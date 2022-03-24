create table cliente(

    id bigint not null auto_increment,
    nome varchar(255) not null,
    email varchar(100) not null,
    telefone varchar(17) not null,
    
    primary key (id)
);