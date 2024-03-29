*BookingServiceTest.java* for unit test

*BookingApplication.java* for running server at port 8080

**Example CURL**
```
curl --location 'localhost:8080/calculate-table' \
--header 'Content-Type: application/json' \
--data '{

    "bookingStartDate": "30/03/2567",
    "reservedTableInfo": [
        {
            "bookerName": "Booker Name",
            "tel": "0877412768",
            "bookingStartTime": "08:00",
            "bookingEndTime": "09:00",
            "customerAmount": 5
        },
        {
            "bookerName": "Booker Name",
            "tel": "0877412768",
            "bookingStartTime": "08:00",
            "bookingEndTime": "09:00",
            "customerAmount": 4
        },
        {
            "bookerName": "Booker Name",
            "tel": "0877412768",
            "bookingStartTime": "09:00",
            "bookingEndTime": "10:00",
            "customerAmount": 8
        }
    ]
}'
```