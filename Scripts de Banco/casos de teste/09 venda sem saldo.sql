--
-- Verifica se uma venda sem saldo pode ser registrada. A venda não deve ser registrada.
-- Nenhum lançamento deve ser registrado para o vendedor.
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
VALUES ("Marcio", "", "", "marcio.barros@gmail.com", "", 0);

-- Pega o identificador do primeiro vendedor
SELECT id INTO @idVendedor FROM usuarios WHERE email = 'marcio.barros@gmail.com';

-- Insere o personagem vendido
INSERT INTO personagens (nome)
VALUES ("MICKEY");

-- Pega o identificador do personagem vendido
SELECT id INTO @idPersonagem FROM personagens WHERE nome = 'MICKEY';

-- credita a conta de personagem do primeiro vendedor
INSERT INTO lancamentosPersonagem (data, idUsuario, idPersonagem, operacao, historico, quantidade, precoUnitario)
VALUES (now(), @idVendedor1, @idPersonagem, 0, 'Saldo inicial', 10, 80.0);

-- registra a oferta de venda do primeiro vendedor
CALL RegistraOrdemVenda(@idVendedor, @idPersonagem, 1000, 200.0, @erro);
CALL Debug("ERRO: ocorreu um erro no registro da venda", @erro <> 0); 

-- verifica a oferta de venda foi registrada
SELECT COUNT(id) INTO @contador FROM ofertas WHERE idUsuario = @IdVendedor;
CALL Debug("ERRO: a oferta de venda nao deveria ter sido registrada", @contador = 0);

-- verifica se existem dois lançamentos para o primeiro vendedor
SELECT COUNT(id) INTO @contador FROM lancamentosPersonagem WHERE idUsuario = @idVendedor;
CALL Debug("ERRO: numero de lancamentos invalido para o primeiro vendedor", @contador = 1);

-- apresenta os resultados
SELECT * FROM DebugMessages;
