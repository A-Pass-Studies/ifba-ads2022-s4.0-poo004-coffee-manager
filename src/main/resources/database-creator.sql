drop schema if exists auth cascade;
drop schema if exists estoque cascade;
drop schema if exists movimento cascade;

create or replace
function on_update_set_current_timestamp()
returns trigger as $$
begin
   NEW.atualizado_em = now();

return new;
end;

$$ language 'plpgsql';

create schema auth;

create type auth.usuario_tipo as enum ('ADMIN', 'GERENTE', 'ATENDENTE');

create table auth.usuarios (
	id bigserial not null primary key,
	username varchar(149) not null,
	nome_completo varchar(128) not null,
	senha varchar(80) not null,
	tipo auth.usuario_tipo not null,
	criado_em timestamp default current_timestamp,
	atualizado_em timestamp not null default current_timestamp,
	
	constraint usuarios_username_uq unique (username)
);

create trigger on_update_usuarios_current_timestamp before
update
	on
	auth.usuarios for each row execute procedure 
    on_update_set_current_timestamp();

create schema estoque;

create table estoque.itens (
	id bigserial not null primary key,
	descricao varchar(128) not null,
	valor_venda_atual decimal(9,2) not null,
	
	cadastro_usuario_id bigint not null,
	criado_em timestamp not null default current_timestamp,
	atualizado_em timestamp not null default current_timestamp,

	constraint valor_venda_atual_valido check (valor_venda_atual >= 0),
	constraint itens_cadastro_ususario_id_fk foreign key (cadastro_usuario_id) references auth.usuarios (id)
);

create trigger on_update_itens_current_timestamp before
update
	on
	estoque.itens for each row execute procedure 
    on_update_set_current_timestamp();

create schema movimento;

create table movimento.compras (
	id bigserial not null primary key,
	item_id bigint not null,
	valor_unitario decimal(9,2) not null,
	qtd integer not null,
	data_compra date not null,
	vencimento date null,
	cadastro_usuario_id bigint not null,
	criado_em timestamp not null default current_timestamp,
	atualizado_em timestamp not null default current_timestamp,
	
	constraint valor_unitario_valido check (valor_unitario >= 0),
	constraint qtd_valido check (qtd > 0),
	constraint compras_item_id_fk foreign key (item_id) references estoque.itens(id),
	constraint compraas_cadastro_ususario_id_fk foreign key(cadastro_usuario_id) references auth.usuarios(id)

);

create trigger on_update_compras_current_timestamp before
update
	on
	movimento.compras for each row execute procedure 
    on_update_set_current_timestamp();


create table movimento.vendas (
	id bigserial not null primary key,
	cadastro_usuario_id bigint not null,
	criado_em timestamp not null default current_timestamp,
	constraint vendas_cadastro_ususario_id_fk foreign key(cadastro_usuario_id) references auth.usuarios(id)
);

create table movimento.venda_itens (
	venda_id bigint not null,
	item_id bigint not null,
	qtd int not null,
	valor_unitario_venda decimal(9,2) not null,
	
	primary key(venda_id, item_id),
	constraint qtd_valido check(qtd > 0),
	constraint valor_venda_valido check(valor_unitario_venda >= 0),
	constraint venda_itens_item_id_fk foreign key (item_id) references estoque.itens(id)
);

-- testes

INSERT INTO auth.usuarios (username, nome_completo, senha, tipo) VALUES
('admin', 'Anderson', 'admin123', 'ADMIN'),
('gerente01', 'Robert', 'senha456', 'GERENTE'),
('atendente01', 'Arthur', 'senha789', 'ATENDENTE'),
('atendente02', 'Joana', 'senha101', 'ATENDENTE');

INSERT INTO estoque.itens (descricao, valor_venda_atual, cadastro_usuario_id) VALUES
('Café Expresso', 5.00, 1),
('Cappuccino', 7.50, 1),
('Pão de Queijo', 3.00, 2),
('Croissant', 4.50, 2),
('Torta de Limão', 6.00, 2),
('Bolo de Cenoura', 5.50, 2),
('Chá Gelado', 4.00, 3),
('Suco Natural', 6.50, 3),
('Café Latte', 6.00, 1),
('Espresso Duplo', 7.00, 1),
('Brownie', 5.00, 2),
('Sanduíche Natural', 8.00, 3),
('Cookie de Chocolate', 3.50, 2),
('Muffin de Blueberry', 4.50, 2),
('Cheesecake', 7.00, 2);

INSERT INTO movimento.compras (item_id, valor_unitario, qtd, data_compra, vencimento, cadastro_usuario_id) VALUES
(1, 3.50, 100, '2025-03-01', '2025-09-01', 1),
(2, 5.00, 50, '2025-03-02', '2025-08-15', 1),
(3, 2.00, 200, '2025-03-03', '2025-07-20', 2),
(4, 3.00, 150, '2025-03-04', '2025-07-30', 2),
(5, 4.00, 80, '2025-03-05', '2025-08-10', 2),
(6, 3.50, 100, '2025-03-06', '2025-08-25', 2),
(7, 2.50, 120, '2025-03-07', '2025-08-05', 3),
(8, 4.50, 70, '2025-03-08', '2025-08-15', 3),
(9, 5.00, 60, '2025-03-09', '2025-08-20', 1),
(10, 6.00, 50, '2025-03-10', '2025-08-30', 1),
(11, 4.00, 90, '2025-03-11', '2025-09-05', 2),
(12, 6.50, 40, '2025-03-12', '2025-08-25', 3),
(13, 2.50, 150, '2025-03-13', '2025-07-25', 2),
(14, 3.50, 100, '2025-03-14', '2025-08-10', 2),
(15, 5.00, 80, '2025-03-15', '2025-09-01', 2);


