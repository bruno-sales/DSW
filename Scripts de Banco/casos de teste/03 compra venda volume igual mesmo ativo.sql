--
-- Verifica se uma oferta de venda e uma oferta de compra idênticas são casadas 
-- e executadas completamente. Verifica se o personagem é bloquado no vendedor e
-- se nenhum lançamento é gerado no comprador.
--

-- limpa o banco de dados
SET foreign_key_checks = 0;
TRUNCATE TABLE lancamentosPersonagem;
TRUNCATE TABLE lancamentosDinheiro;
TRUNCATE TABLE transferencias;
TRUNCATE TABLE casamentosOferta;
TRUNCATE TABLE ofertas;
TRUNCATE TABLE personagens;
TRUNCATE TABLE tokens;
TRUNCATE TABLE usuarios;
TRUNCATE TABLE DebugMessages;
SET foreign_key_checks = 1;

-- Insere o vendedor 
INSERT INTO usuarios (nome, telefone, cpf, email, senha, administrator)
VALUES ("Seller #1", "", "1", "seller1@gmail.com", "", 0);

-- Pega o identificador do vendedor
SELECT id INTO @idVendedor FROM usuarios WHERE email = 'seller1@gmail.com';

-- Insere o comprador 
INSERT INTO usuarios (nome, telefone, cpf, email, senha, administrator)
VALUES ("Buyer #1", "", "2", "buyer1@gmail.com", "", 0);

-- Pega o identificador do comprador
SELECT id INTO @idComprador FROM usuarios WHERE email = 'buyer1@gmail.com';

-- Insere o ativo vendido
INSERT INTO personagens (nome)
VALUES ("MICKEY");

-- Pega o identificador do ativo vendido
SELECT id INTO @idPersonagem FROM personagens WHERE nome = 'MICKEY';

-- credita a conta de ativo do vendedor
INSERT INTO lancamentosPersonagem (data, idUsuario, idPersonagem, operacao, historico, quantidade, precoUnitario)
VALUES (now(), @idVendedor, @idPersonagem, 0, 'Saldo inicial', 1000, 0.0);

-- credita a conta de moeda do comprador
INSERT INTO lancamentosDinheiro (data, idUsuario, operacao, historico, valor)
VALUES (now(), @idComprador, 0, 'Saldo inicial', 100000);

-- registra uma oferta de venda
CALL RegistraOrdemVenda(@idVendedor, @idPersonagem, 1000, 100.0, @erro);
CALL Debug("ERRO: ocorreu um erro no registro da venda", @erro = 0); 

-- registra uma oferta de compra
CALL RegistraOrdemCompra(@idComprador, @idPersonagem, 1000, 100.0, @erro);
CALL Debug("ERRO: ocorreu um erro no registro da compra", @erro = 0); 
CALL Debug(@erro, @erro = 0); 

-- verifica se a oferta de venda foi marcada como executada
SELECT status INTO @status FROM ofertas WHERE tipo = 0;
CALL Debug("ERRO: status da oferta de venda nao foi alterado para EXECUTADO depois da execucao", @status = 1);

-- verifica se foi criado um registro de casamento das ordens
SELECT count(*) INTO @contador FROM casamentosoferta;
CALL Debug("ERRO: oferta de compra nao relacionada a oferta de venda depois da execucao", @contador = 1); 

-- verifica se a oferta de compra foi marcada como executada
SELECT status INTO @status FROM ofertas WHERE tipo = 1;
CALL Debug("ERRO: status da oferta de compra nao foi alterado para EXECUTADO depois da execucao", @status = 1);

-- verifica se foram gerados lançamento para o vendedor
SELECT COUNT(id) INTO @contador FROM lancamentosPersonagem WHERE idUsuario = @IdVendedor;
CALL Debug("ERRO: numero de lancamentos invalido para o vendedor", @contador = 4);

-- verifica se foi realizado o bloqueio do ativo no vendedor
SELECT count(*) INTO @contador FROM lancamentosPersonagem WHERE idUsuario = @idVendedor AND operacao = 2 AND quantidade = 1000 AND precoUnitario = 100;
CALL Debug("ERRO: nao encontrei o lancamento de bloqueio do ativo no vendedor", @contador = 1);

-- verifica se foi realizado o desbloqueio do ativo no vendedor
SELECT count(*) INTO @contador FROM lancamentosPersonagem WHERE idUsuario = @idVendedor AND operacao = 3 AND quantidade = 1000 AND precoUnitario = 100;
CALL Debug("ERRO: nao encontrei o lancamento de desbloqueio do ativo no vendedor", @contador = 1);

-- verifica se foi realizado o saque do ativo no vendedor
SELECT count(*) INTO @contador FROM lancamentosPersonagem WHERE idUsuario = @idVendedor AND operacao = 1 AND quantidade = 1000 AND precoUnitario = 100;
CALL Debug("ERRO: nao encontrei o lancamento de saque do ativo no vendedor", @contador = 1);

-- verifica se foi realizado o depósito do dinheiro no vendedor
SELECT count(*) INTO @contador FROM lancamentosDinheiro WHERE idUsuario = @idVendedor AND operacao = 0 AND valor = 100000;
CALL Debug("ERRO: nao encontrei o lancamento de deposito do dinheiro no vendedor", @contador = 1);

-- verifica se o saldo do ativo no vendedor está correto
CALL CalculaSaldoDisponivelPersonagem(@idVendedor, @idPersonagem, @saldoVendedor);
CALL Debug("ERRO: saldo de ativo invalido no vendedor", @saldoVendedor = 0);

-- verifica se foi realizado o bloqueio da moeda no comprador
SELECT count(*) INTO @contador FROM lancamentosDinheiro WHERE idUsuario = @idComprador AND operacao = 2 AND valor = 100000;
CALL Debug("ERRO: nao encontrei o lancamento de bloqueio da moeda no comprador", @contador = 1);

-- verifica se foi realizado o desbloqueio do ativo no comprador
SELECT count(*) INTO @contador FROM lancamentosDinheiro WHERE idUsuario = @idComprador AND operacao = 3 AND valor = 100000;
CALL Debug("ERRO: nao encontrei o lancamento de desbloqueio da moeda no comprador", @contador = 1);

-- verifica se foi realizado o saque do ativo no comprador
SELECT count(*) INTO @contador FROM lancamentosDinheiro WHERE idUsuario = @idComprador AND operacao = 1 AND valor = 100000;
CALL Debug("ERRO: nao encontrei o lancamento de saque da moeda no comprador", @contador = 1);

-- verifica se foi realizado o depósito do personagem no comprador
SELECT count(*) INTO @contador FROM lancamentosPersonagem WHERE idUsuario = @idComprador AND operacao = 0 AND quantidade = 1000 AND precoUnitario = 100;
CALL Debug("ERRO: nao encontrei o lancamento de deposito do ativo no comprador", @contador = 1);

-- verifica se o saldo da moeda no comprador está correto
CALL CalculaSaldoDisponivelDinheiro(@idComprador, @saldoComprador);
CALL Debug("ERRO: saldo de moeda invalido no comprador", @saldoComprador = 0);

-- apresenta os resultado
SELECT * FROM DebugMessages;
