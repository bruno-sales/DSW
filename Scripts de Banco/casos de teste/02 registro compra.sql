--
-- Testa o registro de uma oferta de compra
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

-- Insere o comprador 
INSERT INTO usuarios (nome, telefone, cpf, email, senha, administrator)
VALUES ("Marcio", "", "", "marcio.barros@gmail.com", "", 0);

-- Pega o identificador do comprador
SELECT id INTO @idComprador FROM usuarios WHERE email = 'marcio.barros@gmail.com';

-- Insere o personagem
INSERT INTO personagens (nome)
VALUES ("MICKEY");

-- Pega o identificador do personagem
SELECT id INTO @idPersonagem FROM personagens WHERE nome = 'MICKEY';

-- credita a conta de moeda do comprador
INSERT INTO lancamentosDinheiro (data, idUsuario, operacao, historico, valor)
VALUES (now(), @idComprador, 0, 'Saldo inicial', 100000);

-- registra uma oferta de compra
CALL RegistraOrdemCompra (@idComprador, @idPersonagem, 1000, 90.0, @erro);
CALL Debug("ERRO: erro no registro de oferta de compra", @erro = 0); 

-- verifica se a oferta de compra esta aberta
SELECT COUNT(id) INTO @contador FROM ofertas WHERE tipo=1 AND status=0;
CALL Debug("ERRO: erro no numero de compras registradas no sistema", @contador = 1);

-- verifica se nao foi gerado outro lançamento para o comprador
SELECT COUNT(id) INTO @contador FROM lancamentosDinheiro WHERE idUsuario = @idComprador;
CALL Debug("ERRO: numero de lancamentos invalido para o comprador", @contador = 2);

-- verifica se o saldo do ativo no comprador está correto
CALL CalculaSaldoDisponivelPersonagem(@idComprador, @idPersonagem, @saldoComprador);
CALL Debug("ERRO: saldo de ativo invalido no comprador", @saldoComprador = 0);

-- verifica se o saldo do ativo no comprador está correto
CALL CalculaSaldoDisponivelDinheiro(@idComprador, @saldoComprador);
CALL Debug("ERRO: saldo de moeda invalido no comprador", @saldoComprador = 10000);

-- apresenta os resultado
SELECT * FROM DebugMessages;
