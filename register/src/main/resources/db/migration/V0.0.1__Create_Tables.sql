-- public.restaurant definition

-- Drop table
-- DROP TABLE public.restaurant;

CREATE TABLE public.restaurant (
	restaurant_id uuid NOT NULL,
	created_at date NOT NULL,
	"name" varchar(255) NULL,
	"owner" varchar(255) NULL,
	updated_at date NOT NULL,
	CONSTRAINT restaurant_pkey PRIMARY KEY (restaurant_id),
	CONSTRAINT uk_i6u3x7opncroyhd755ejknses UNIQUE (name)
);

-- public.dish definition

-- Drop table
-- DROP TABLE public.dish;

CREATE TABLE public.dish (
	dish_id uuid NOT NULL,
	restaurant_id uuid NOT NULL,
	description varchar(255) NULL,
	"name" varchar(255) NULL,
	price float8 NULL,
	CONSTRAINT dish_pkey PRIMARY KEY (dish_id, restaurant_id)
);


-- public.dish foreign keys
ALTER TABLE public.dish ADD CONSTRAINT fkt13glsbe9ivpka00hbeg539cv FOREIGN KEY (restaurant_id) REFERENCES public.restaurant(restaurant_id) ON DELETE CASCADE;


-- public.address definition

-- Drop table
-- DROP TABLE public.address;

CREATE TABLE public.address (
	restaurant_id uuid NOT NULL,
	latitude float8 NULL,
	longitude float8 NULL,
	CONSTRAINT address_pkey PRIMARY KEY (restaurant_id),
	CONSTRAINT uk8unp71l1qqu2fqir4qhhjp5hb UNIQUE (latitude, longitude)
);


-- public.address foreign keys
ALTER TABLE public.address ADD CONSTRAINT fk6218puogn7aamlck6quuf39ll FOREIGN KEY (restaurant_id) REFERENCES public.restaurant(restaurant_id);
