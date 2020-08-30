# order-management
it contains 2 microservices 
    a.) order-management-orderitem
    b.) order-management-orders
Both spring boot applications are gradle based. 
    a.) order-management-orderitem -> Running on default port 8080
    b.) order-management-orders -> Running on port 8081
Swagger is enabled for both the application.
Total 3-tables are there:
    1.) order-management-orders:
              a.) LINE_ITEM
              b.) ORDER_DATA
    2.) order-management-orderitem:
              a.) ORDER_ITEM  
