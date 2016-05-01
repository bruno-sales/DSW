--
-- Registra uma oferta de venda e uma oferta de compra com quantidade maior que a venda (mesmo ativo).
-- Verifica se a compra foi desmembrada em duas operações. A venda deve ser executada. A primeira
-- compra deve ser executada. A segunda compra deve permanecer aberta. O ativo deve ser bloqueado
-- no vendedor. Nenhum lançamento deve ser gerado no comprador.
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
VALUES (now(), @idComprador, 0, 'Saldo inicial', 1000000);

-- registra uma oferta de venda
CALL RegistraOrdemVenda(@idVendedor, @idPersonagem, 1000, 100.0, @erro);
CALL Debug("ERRO: ocorreu um erro no registro da venda", @erro = 0); 

-- registra uma oferta de compra
CALL RegistraOrdemCompra(@idComprador, @idPersonagem, 10000, 100.0, @erro);
CALL Debug("ERRO: ocorreu um erro no registro da compra", @erro = 0); 

-- verifica se existe uma oferta de venda
SELECT COUNT(id) INTO @contador FROM ofertas WHERE tipo=0;
CALL Debug("ERRO: a oferta de venda original foi desmembrada erroneamente", @contador = 1);

-- verifica se a oferta de venda foi marcada como executada
SELECT count(*) INTO @contador FROM ofertas WHERE tipo = 0 AND status=1 AND quantidade=1000;
CALL Debug("ERRO: status da oferta de venda nao foi alterado para EXECUTADO depois da execucao", @contador = 1);

-- verifica se foi realizado o bloqueio do ativo no vendedor
SELECT count(*) INTO @contador FROM lancamentosPersonagem WHERE idUsuario = @idVendedor AND idPersonagem = @idPersonagem AND operacao = 2 AND quantidade = 1000 AND precoUnitario = 100;
CALL Debug("ERRO: nao encontrei o lancamento de bloqueio do ativo no vendedor", @contador = 1);

-- verifica se foi realizado o desbloqueio do ativo no vendedor
SELECT count(*) INTO @contador FROM lancamentosPersonagem WHERE idUsuario = @idVendedor AND idPersonagem = @idPersonagem AND operacao = 3 AND quantidade = 1000 AND precoUnitario = 100;
CALL Debug("ERRO: nao encontrei o lancamento de desbloqueio do ativo no vendedor", @contador = 1);

-- verifica se foi realizado o saque do ativo no vendedor
SELECT count(*) INTO @contador FROM lancamentosPersonagem WHERE idUsuario = @idVendedor AND idPersonagem = @idPersonagem AND operacao = 1 AND quantidade = 1000 AND precoUnitario = 100;
CALL Debug("ERRO: nao encontrei o lancamento de saque do ativo no vendedor", @contador = 1);

-- verifica se o saldo do ativo no vendedor está correto
CALL CalculaSaldoDisponivelPersonagem(@idVendedor, @idPersonagem, @saldoVendedor);
CALL Debug("ERRO: saldo de ativo invalido no vendedor", @saldoVendedor = 0);

-- verifica se a oferta de compra foi desmembrada
SELECT COUNT(id) INTO @contador FROM ofertas WHERE tipo=1;
CALL Debug("ERRO: a oferta de compra nao foi desmembrada", @contador = 2);

-- verifica se foi realizado o bloqueio da moeda no comprador
SELECT count(*) INTO @contador FROM lancamentosDinheiro WHERE idUsuario = @idComprador AND operacao = 2 AND valor = 1000000;
CALL Debug("ERRO: nao encontrei o lancamento de bloqueio da moeda no comprador", @contador = 1);

-- verifica se foi realizado o desbloqueio do ativo no comprador
SELECT count(*) INTO @contador FROM lancamentosDinheiro WHERE idUsuario = @idComprador AND operacao = 3 AND valor = 1000000;
CALL Debug("ERRO: nao encontrei o lancamento de desbloqueio da moeda no comprador", @contador = 1);

-- verifica se foi realizado o bloqueio do restante da moeda no comprador
SELECT count(*) INTO @contador FROM lancamentosDinheiro WHERE idUsuario = @idComprador AND operacao = 2 AND valor = 900000;
CALL Debug("ERRO: nao encontrei o lancamento de bloqueio da moeda no comprador", @contador = 1);

-- verifica se foi realizado o saque do ativo no comprador
SELECT count(*) INTO @contador FROM lancamentosDinheiro WHERE idUsuario = @idComprador AND operacao = 1 AND valor = 100000;
CALL Debug("ERRO: nao encontrei o lancamento de saque da moeda no comprador", @contador = 1);

-- verifica se o saldo da moeda no comprador está correto
CALL CalculaSaldoDisponivelDinheiro(@idComprador, @saldoComprador);
CALL Debug("ERRO: saldo de moeda invalido no comprador", @saldoComprador = 0);

-- apresenta os resultado
SELECT * FROM DebugMessages;
