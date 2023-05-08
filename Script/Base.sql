CREATE table Admin(
                      idAdmin serial primary key,
                      mail varchar(25) not null,
                      mdp varchar(25) not null
);
insert into Admin (mail,mdp) values ('Admin','Admin');

CREATE table Utilisateur(
                       idUser serial primary key,
                       mail varchar(50) not null,
                       mdp varchar(50) not null
);
insert into Utilisateur (mail,mdp) values ('User','User');

CREATE table article(
                        idArticle serial primary key,
                        titre varchar(50) NOT NULL ,
                        contenu text NOT NULL,
                        datePublication timestamp NOT NULL,
                        dateCreation timestamp default current_timestamp
);

create table LikeArticle(
    idArticle integer,
    idUser integer,
    UNIQUE(idArticle,idUser)
);
alter table LikeArticle add foreign key (idArticle) references article(idArticle);
alter table LikeArticle add foreign key (idUser) references Utilisateur(idUser);

create table Actualite(
    annonce text,
    idArticle integer,
    dateCreation timestamp default current_timestamp
);
alter table Actualite add foreign key (idArticle) references article(idArticle);

