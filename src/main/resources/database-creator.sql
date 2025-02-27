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

INSERT INTO auth.usuarios
(username, senha, tipo)
VALUES('admin', 'admin123', 'ADMIN');


