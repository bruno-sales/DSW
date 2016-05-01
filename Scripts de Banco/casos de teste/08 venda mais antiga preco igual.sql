--
-- Verifica se a oferta mais antiga é selecionada quando existem duas ofertas de venda com o mesmo preço.
-- Considera dois vendedores e um comprador. A primeira oferta de venda e a segunda tem o mesmo preço.
-- A terceira oferta é mais cara. A primeira oferta deve ser executada, ficando as demais abertas.
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

-- Insere o primeiro vendedor 
INSERT INTO usuarios (nome, telefone, cpf, email, senha, administrator)
VALUES ("Marcio", "", "1", "marcio.barros@gmail.com", "", 0);

-- Pega o identificador do primeiro vendedor
SELECT id INTO @idVendedor1 FROM usuarios WHERE email = 'marcio.barros@gmail.com';

-- Insere o segundo vendedor 
INSERT INTO usuarios (nome, telefone, cpf, email, senha, administrator)
VALUES ("Fulano", "", "2", "fulano@gmail.com", "", 0);

-- Pega o identificador do segundo vendedor
SELECT id INTO @idVendedor2 FROM usuarios WHERE email = 'fulano@gmail.com';

-- Insere o comprador 
INSERT INTO usuarios (nome, telefone, cpf, email, senha, administrator)
VALUES ("Comprador", "", "3", "comprador@gmail.com", "", 0);

-- Pega o identificador do comprador
SELECT id INTO @idComprador FROM usuarios WHERE email = 'comprador@gmail.com';

-- Insere o personagem vendido
INSERT INTO personagens (nome)
VALUES ("MICKEY");

-- Pega o identificador do personagem vendido
SELECT id INTO @idPersonagem FROM personagens WHERE nome = 'MICKEY';

-- credita a conta de personagem do primeiro vendedor
INSERT INTO lancamentosPersonagem (data, idUsuario, idPersonagem, operacao, historico, quantidade, precoUnitario)
VALUES (now(), @idVendedor1, @idPersonagem, 0, 'Saldo inicial', 10000, 80.0);

-- credita a conta de personagem do segundo vendedor
INSERT INTO lancamentosPersonagem (data, idUsuario, idPersonagem, operacao, historico, quantidade, precoUnitario)
VALUES (now(), @idVendedor2, @idPersonagem, 0, 'Saldo inicial', 10000, 80.0);

-- credita a conta de personagem do segundo vendedor
INSERT INTO lancamentosDinheiro (data, idUsuario, operacao, historico, valor)
VALUES (now(), @idComprador, 0, 'Saldo inicial', 1000000);

-- registra a oferta de venda do primeiro vendedor
CALL RegistraOrdemVenda(@idVendedor1, @idPersonagem, 1000, 200.0, @erro);
CALL Debug("ERRO: ocorreu um erro no registro da venda", @erro = 0); 

-- verifica a oferta de venda foi registrada
SELECT COUNT(id) INTO @contador FROM ofertas WHERE idUsuario = @IdVendedor1 AND tipo=0 AND status=0;
CALL Debug("ERRO: a primeira oferta de venda nao foi registrada corretamente", @contador = 1);

-- verifica se existem dois lançamentos para o primeiro vendedor
SELECT COUNT(id) INTO @contador FROM lancamentosPersonagem WHERE idUsuario = @idVendedor1;
CALL Debug("ERRO: numero de lancamentos invalido para o primeiro vendedor", @contador = 2);

-- verifica se foi realizado o bloqueio do ativo no primeiro vendedor
SELECT COUNT(id) INTO @contador FROM lancamentosPersonagem WHERE idUsuario = @idVendedor1 AND quantidade = 1000 AND precoUnitario = 200 AND operacao = 2;
CALL Debug("ERRO: lancamento de bloqueio do ativo no vendedor nao encontrado", @contador = 1);

-- registra a oferta de venda do segundo vendedor
CALL RegistraOrdemVenda(@idVendedor2, @idPersonagem, 1000, 180.0, @erro);
CALL Debug("ERRO: ocorreu um erro no registro da segunda venda", @erro = 0); 

-- verifica a oferta de venda foi registrada
SELECT COUNT(id) INTO @contador FROM ofertas WHERE idUsuario = @IdVendedor2 AND tipo=0 AND status=0;
CALL Debug("ERRO: a segunda oferta de venda nao foi registrada corretamente", @contador =1);

-- verifica se existem tres lançamentos para o segundo vendedor
SELECT COUNT(id) INTO @contador FROM lancamentosPersonagem WHERE idUsuario = @idVendedor2;
CALL Debug("ERRO: numero de lancamentos invalido para o segundo vendedor", @contador = 2);

-- verifica se foi realizado o bloqueio do ativo no segundo vendedor
SELECT COUNT(id) INTO @contador FROM lancamentosPersonagem WHERE idUsuario = @idVendedor2 AND quantidade = 1000 AND precoUnitario = 180 AND operacao = 2;
CALL Debug("ERRO: lancamento de bloqueio do ativo no vendedor nao encontrado", @contador = 1);

-- registra uma terceira oferta de venda
CALL RegistraOrdemVenda(@idVendedor1, @idPersonagem, 1000, 200.0, @erro);
CALL Debug("ERRO: ocorreu um erro no registro da terceira venda", @erro = 0); 

-- verifica a oferta de venda foi registrada
SELECT COUNT(id) INTO @contador FROM ofertas WHERE idUsuario = @IdVendedor1 AND tipo=0 AND status=0;
CALL Debug("ERRO: a terceira oferta de venda nao foi registrada corretamente", @contador = 2);

-- verifica se foi realizado o bloqueio do ativo no primeiro vendedor
SELECT COUNT(id) INTO @contador FROM lancamentosPersonagem WHERE idUsuario = @idVendedor1 AND quantidade = 1000 AND precoUnitario = 200 AND operacao = 2;
CALL Debug("ERRO: lancamento de bloqueio do ativo no vendedor nao encontrado", @contador = 2);

-- registra uma oferta de compra com preço muito alto ...
CALL RegistraOrdemCompra(@idComprador, @idPersonagem, 1000, 350.0, @erro);
CALL Debug("ERRO: ocorreu um erro no registro da compra", @erro = 0); 

-- deve ter pego a oferta de venda mais antiga com menor preço
SELECT COUNT(id) INTO @contador FROM ofertas WHERE id = 1 AND tipo = 0 AND status = 1 AND precoUnitario = 200.0 AND idUsuario = @IdVendedor1;
CALL Debug("ERRO: a oferta de venda com preco mais baixo deveria ter sido executada", @contador <> 1);

SELECT COUNT(id) INTO @contador FROM ofertas WHERE status = 0 AND tipo = 0;
CALL Debug("ERRO: duas ofertas de venda deveriam permanecer em aberto", @contador = 2);

SELECT COUNT(id) INTO @contador FROM ofertas WHERE status = 1 AND tipo = 1 AND precoUnitario = 350.0;
CALL Debug("ERRO: a oferta de compra deveria ter sido executada", @contador = 1);

-- apresenta os resultado
SELECT * FROM DebugMessages;
