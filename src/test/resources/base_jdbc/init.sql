CREATE TABLE person(
    id UUID PRIMARY KEY,
    name VARCHAR(200)
);

CREATE TABLE card(
    id UUID PRIMARY KEY,
    person_id UUID,
    card_number VARCHAR(40)
);

INSERT INTO person (id, name) VALUES
    ('c1a7025a-3f61-4542-a9a5-2968d406ccab', 'Ivan'),
    ('999aa405-d7eb-4a6e-88a5-272eaeefdd18', 'Mikhail'),
    ('14a88c98-d710-49ca-99c2-6c3749311b71', 'Natasha'),
    ('ca432105-e37a-4e45-bbbd-c92b72d41062', 'Dmitri'),
    ('8baf0329-3f2e-4823-9460-32a51deab1df', 'Anastasia');

INSERT INTO card (id, person_id, card_number) VALUES
    ('d3c46d24-387e-47ef-8c4b-1fe16d6ea8c1', 'c1a7025a-3f61-4542-a9a5-2968d406ccab', '4532875167321048'),
    ('6a93d9b5-3497-4aac-8103-944b45ff682c', 'c1a7025a-3f61-4542-a9a5-2968d406ccab', '5376928401356829'),
    ('2e9945a3-7fbf-425f-b06b-4f64f07844b1', 'c1a7025a-3f61-4542-a9a5-2968d406ccab', '371449635398431'),

    ('ec50524a-21f1-450a-8c1d-2a9e4810697e', '999aa405-d7eb-4a6e-88a5-272eaeefdd18', '6011168375940283'),
    ('638cb0a4-6b04-466a-8cd6-37cac94190b7', '999aa405-d7eb-4a6e-88a5-272eaeefdd18', '3528345687921560'),

    ('263f1d1a-a641-4013-a15f-569615a221e6', '14a88c98-d710-49ca-99c2-6c3749311b71', '5555345678901234'),

    ('65caae33-713b-4a2b-b178-5f1989bbe468', 'ca432105-e37a-4e45-bbbd-c92b72d41062', '4024007198325467'),
    ('1e5250ca-17e1-4a64-87c0-97fb79d620aa', 'ca432105-e37a-4e45-bbbd-c92b72d41062', '364389012457832'),
    ('fb933fbe-dae2-4d8f-be96-9a123ec8de6e', 'ca432105-e37a-4e45-bbbd-c92b72d41062', '4916738502469135'),

    ('b0bfc959-76f3-4eab-a759-e5994cbed77a', '8baf0329-3f2e-4823-9460-32a51deab1df', '300578462901457'),
    ('04c7ec54-5a89-4b2e-934c-63610c857b60', '8baf0329-3f2e-4823-9460-32a51deab1df', '503896472130548');