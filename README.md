# ERP Sistemi Ödevi
## Modeller
- Product
- Stock
- Customer
- Order
- OrderItem
- KeyValue
- Bill
## Sıralama
- İlk olarak KeyValue kısmına KDV ismini ve değerlerini post ediyoruz.
- Product oluşturuyoruz.
- Product için Stock oluşturuyoruz.
- Customer oluşturuyoruz.
- Order oluşturuyoruz.
- Order'a OrderItem ekliyoruz.(Order'a ürün ekleme işlemi)
- Order'ın status kısmını APPROVED yapıyoruz ve bize bill oluşturuyor.
- Bill kısmında GET /bill yaparak veya GET /bill/order-uuid{uuid} ile faturaya erişebiliriz.
- GET /bill/pdf/{uuid} yaptığımızda bize girdiğimiz bilgilerden dinamik PDF üretiyor ve indiriyor.
#  ![invoice](https://github.com/Patika-X-Allianz-Bootcamp/week-8-hw/assets/99567926/42c8b8e5-03f4-4dfd-a6d5-beb6c6ae8815)
