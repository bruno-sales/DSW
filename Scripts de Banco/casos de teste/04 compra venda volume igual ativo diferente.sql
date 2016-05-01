--
-- Verifica se uma oferta de venda e uma oferta de compra com volume identico e
-- preco compativel, mas sobre ativos diferentes nao sao casadas e executadas.
-- Verifica se o ativo é bloquado no vendedor e se nenhum lançamento é gerado 
-- no comprador. Verifica se as ofertas continuam abertas.
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
SELECT id INTO @idPersonagemVenda FROM personagens WHERE nome = 'MICKEY';

-- Insere o ativo comprado
INSERT INTO personagens (nome)
VALUES ("DONALD");

-- Pega o identificador do ativo comprado
SELECT id INTO @idPersonagemCompra FROM personagens WHERE nome = 'DONALD';

-- credita a conta de ativo do vendedor
INSERT INTO lancamentosPersonagem (data, idUsuario, idPersonagem, operacao, historico, quantidade, precoUnitario)
VALUES (now(), @idVendedor, @idPersonagemVenda, 0, 'Saldo inicial', 1000, 0.0);

-- credita a conta de moeda do comprador
INSERT INTO lancamentosDinheiro (data, idUsuario, operacao, historico, valor)
VALUES (now(), @idComprador, 0, 'Saldo inicial', 100000);

-- registra uma oferta de venda
CALL RegistraOrdemVenda(@idVendedor, @idPersonagemVenda, 1000, 100.0, @erro);
CALL Debug("ERRO: ocorreu um erro no registro da venda", @erro = 0); 

-- registra uma oferta de compra
CALL RegistraOrdemCompra(@idComprador, @idPersonagemCompra, 1000, 100.0, @erro);
CALL Debug("ERRO: ocorreu um erro no registro da compra", @erro = 0); 

-- verifica se a oferta de venda está marcada como aberta
SELECT status INTO @status FROM ofertas WHERE tipo = 0;
CALL Debug("ERRO: status da oferta de venda nao esta aberta depois da execucao", @status = 0);

-- verifica se a oferta de compra está marcada como aberta
SELECT status INTO @status FROM ofertas WHERE tipo = 1;
CALL Debug("ERRO: status da oferta de compra nao esta aberta depois da execucao", @status = 0);

-- verifica se foi realizado o bloqueio do ativo no vendedor
SELECT count(*) INTO @contador FROM lancamentosPersonagem WHERE idUsuario = @idVendedor AND idPersonagem = @idPersonagemVenda AND operacao = 2 AND quantidade = 1000 AND precoUnitario = 100;
CALL Debug("ERRO: nao encontrei o lancamento de bloqueio do ativo no vendedor", @contador = 1);

-- verifica se foi realizado o desbloqueio do ativo no vendedor
SELECT count(*) INTO @contador FROM lancamentosPersonagem WHERE idUsuario = @idVendedor AND idPersonagem = @idPersonagemVenda AND operacao = 3 AND quantidade = 1000 AND precoUnitario = 100;
CALL Debug("ERRO: encontrei o lancamento de desbloqueio do ativo no vendedor", @contador = 0);

-- verifica se foi realizado o saque do ativo no vendedor
SELECT count(*) INTO @contador FROM lancamentosPersonagem WHERE idUsuario = @idVendedor AND idPersonagem = @idPersonagemVenda AND operacao = 1 AND quantidade = 1000 AND precoUnitario = 100;
CALL Debug("ERRO: encontrei o lancamento de saque do ativo no vendedor", @contador = 0);

-- verifica se o saldo do ativo no vendedor está correto
CALL CalculaSaldoDisponivelPersonagem(@idVendedor, @idPersonagem, @saldoVendedor);
CALL Debug("ERRO: saldo de ativo invalido no vendedor", @saldoVendedor = 0);

-- verifica se foi realizado o bloqueio da moeda no comprador
SELECT count(*) INTO @contador FROM lancamentosDinheiro WHERE idUsuario = @idComprador AND operacao = 2 AND valor = 100000;
CALL Debug("ERRO: nao encontrei o lancamento de bloqueio da moeda no comprador", @contador = 1);

-- verifica se foi realizado o desbloqueio do ativo no comprador
SELECT count(*) INTO @contador FROM lancamentosDinheiro WHERE idUsuario = @idComprador AND operacao = 3 AND valor = 100000;
CALL Debug("ERRO: encontrei o lancamento de desbloqueio da moeda no comprador", @contador = 0);

-- verifica se foi realizado o saque do ativo no comprador
SELECT count(*) INTO @contador FROM lancamentosDinheiro WHERE idUsuario = @idComprador AND operacao = 1 AND valor = 100000;
CALL Debug("ERRO: encontrei o lancamento de saque da moeda no comprador", @contador = 0);

-- verifica se o saldo da moeda no comprador está correto
CALL CalculaSaldoDisponivelDinheiro(@idComprador, @saldoComprador);
CALL Debug("ERRO: saldo de moeda invalido no comprador", @saldoComprador = 0);

-- apresenta os resultado
SELECT * FROM DebugMessages;
