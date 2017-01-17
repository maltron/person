db.persons.drop();
db.persons.createIndex({"email":1}, {"unique":true});
db.persons.insert({"email":"maltron@gmail.com", "firstName":"Mauricio", "lastName":"Leal"});
db.persons.insert({"email":"evandro@hotmail.com", "firstName":"Evandro", "lastName":"Almeida"});
db.persons.insert({"email":"nadia@gmail.com", "firstName":"Nadia", "lastName":"Ulanova"});
db.persons.insert({"email":"rmissing@redhat.com", "firstName":"Rodrigo", "lastName":"Missiaggia"});
db.persons.insert({"email":"wagner@redhat.com", "firstName":"Wagner", "lastName":"Moura"});
db.persons.insert({"email":"cao@yandex.ru", "firstName":"Sebastian", "lastName":"Cao"});

