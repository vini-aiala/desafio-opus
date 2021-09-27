insert into author(name, email) values
                                    ('Igor Cavalcanti Fernandes', 'igor.fernandes@opus-software.com.br'),
                                    ('Julia Sousa Ribeiro', 'julia.sousa@opus-software.com.br'),
                                    ('Luana Oliveira Barbosa', 'luana.barbosa@opus-software.com.br');

insert into subject(name, category) values
                                        ('Docker', 'INFRA_DEVOPS'),
                                        ('Express.js', 'BACKEND'),
                                        ('Spring Boot', 'BACKEND'),
                                        ('React.js', 'FRONTEND'),
                                        ('Angular', 'FRONTEND'),
                                        ('CSS', 'FRONTEND'),
                                        ('HTML 5', 'FRONTEND'),
                                        ('Machine Learning', 'DATA'),
                                        ('Business Intelligence', 'DATA'),
                                        ('Estatística', 'DATA'),
                                        ('Python', 'SCRIPTING'),
                                        ('JavaScript', 'SCRIPTING'),
                                        ('Java', 'SCRIPTING'),
                                        ('Scrum', 'PROJECT_MANAGEMENT'),
                                        ('Business Agility', 'PROJECT_MANAGEMENT');


insert into question(title, text, status, author_id, subject_id) values
                                                                     ('Erro ao compilar', 'Bom dia, pessoal! Gerei um projeto limpo e quando tento compilar tenho o seguinte erro: "Erro ao compilar". Alguém já passou por isso? Como resolveu?',
                                                                      'ANSWERED', 1, 3),
                                                                     ('Repository inconsistente', 'Boa tarde, pessoal! O meu projeto usa um repository para implementar o CRUD mas os métodos não funcionam como deveriam. Alguém já passou por isso? Como resolveu?',
                                                                      'NOT_ANSWERED', 2, 3);

insert into answer(text, author_id, question_id) values
                                                     ('Oi Igor! Você pode resolver assim.', 2, 1),
                                                     ('Olá Igor! A resposta da Julia funciona mas assim também funciona.', 3, 1);