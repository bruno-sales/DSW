--
-- Testa o registro de uma oferta de venda
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
VALUES ("Marcio", "", "", "marcio.barros@gmail.com", "", 0);

-- Pega o identificador do vendedor
SELECT id INTO @idVendedor FROM usuarios WHERE email = 'marcio.barros@gmail.com';

-- Insere o personagem vendido
INSERT INTO personagens (nome)
VALUES ("MICKEY");

-- Pega o identificador do personagem vendido
SELECT id INTO @idPersonagem FROM personagens WHERE nome = 'MICKEY';

-- credita a conta de personagem do vendedor
INSERT INTO lancamentosPersonagem(data, idUsuario, idPersonagem, operacao, historico, quantidade, precoUnitario)
VALUES (now(), @idVendedor, @idPersonagem, 0, 'Saldo inicial', 1000, 80.0);

-- registra uma oferta de venda
CALL RegistraOrdemVenda(@idVendedor, @idPersonagem, 1000, 100.0, @erro);
CALL Debug("ERRO: erro no registro de oferta de venda", @erro = 0); 

-- verifica se a oferta de venda foi registrada
SELECT COUNT(id) INTO @contador FROM ofertas;
CALL Debug("ERRO: erro no numero de vendas registradas no sistema", @contador = 1);

-- verifica se nao foi gerado outro lançamento para o vendedor
SELECT COUNT(id) INTO @contador FROM lancamentosPersonagem WHERE idUsuario = @IdVendedor;
CALL Debug("ERRO: numero de lancamentos invalido para o vendedor", @contador = 2);

-- verifica se foi realizado o bloqueio do ativo no vendedor
SELECT count(*) INTO @contador FROM lancamentosPersonagem WHERE idUsuario = @idVendedor AND operacao = 2 AND quantidade = 1000 AND precoUnitario = 100;
CALL Debug("ERRO: nao encontrei o lancamento de bloqueio do ativo no vendedor", @contador = 1);

-- verifica se o saldo do ativo no vendedor está correto
CALL CalculaSaldoDisponivelPersonagem(@idVendedor, @idPersonagem, @saldoVendedor);
CALL Debug("ERRO: saldo de ativo invalido no vendedor", @saldoVendedor = 0);

-- apresenta os resultado
SELECT * FROM DebugMessages;
