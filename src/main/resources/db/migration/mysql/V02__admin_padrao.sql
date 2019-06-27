INSERT INTO empresa (id, cnpj, data_atualizacao, data_criacao, razao_social) 
VALUES (NULL, '82198127000121', CURRENT_DATE(), CURRENT_DATE(), 'Kazale IT');

INSERT INTO funcionario (id, cpf, data_atualizacao, data_criacao, email, nome, 
perfil, qtd_horas_almoco, qtd_horas_trabalho_dia, senha, valor_hora, empresa_id) 
VALUES (NULL, '16248890935', CURRENT_DATE(), CURRENT_DATE(), 'admin@admin.com', 'ADMIN', 'ROLE_ADMIN', NULL, NULL, 
'$2a$10$.gxiYiq3Ln1qleh/46tJ0ugMi4Fz1EYQWcaQH.fUUzfs8RY5iiqIa', NULL, 
(SELECT id FROM empresa WHERE cnpj = '82198127000121'));
