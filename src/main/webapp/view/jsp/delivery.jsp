<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://fonts.googleapis.com/css2?family=Varela+Round&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="http://localhost:8080/final/view/css/main.css">
    <link rel="stylesheet" href="http://localhost:8080/final/view/css/delivery.css">
    <script src="main.js"></script>
    <title>Delivery</title>
</head>
<body>
    <jsp:include page="header.jsp"/>
    <div class="body">
        <div class="blank"></div>
        <div class="navigation">
            <a href="http://localhost:8080/final/server/home">Home</a>
            <img src="http://localhost:8080/final/view/media/5f8f84f3d4d23a31c1f2fcae_arrow-right-mini-icon.svg">
            Delivery
        </div>
        <div class="all">
            <div class="a_info">
                <h1>Delivery Info</h1>
                Delivery across Ukraine is carried out by the carrier New mail. Delivery by other carriers is agreed upon individually. The buyer pays for shipping. The cost of delivery by the Carrier on the territory of Ukraine depends on the volume and weight of the ordered goods and is calculated by the carrier separately in each specific case. Shipment of goods by New mail is carried out within 1-2 working days after confirming the order with the manager.
                <h1>Courier delivery across Kiev</h1>
                The cost of delivery in Kiev for small-sized goods is indicated when placing an order. The cost of delivery of bulky goods (bags, pears, etc.) is agreed individually. In Kiev, delivery of most goods is carried out from 09:00 to 19:00 on weekdays, from 10:00 to 18:00 on Saturday.
                <h1>Delivery of bulky goods</h1>
                Delivery of bulky goods (bags, heavy pears, medballs) is carried out on a 100% prepayment basis.Delivery can be carried out by the carrier Nova Poshta to the warehouse or to the address. Shipping cost is paid by the buyer separately when receiving the goods.
                <h1>Other conditions</h1>
                <ul>
                    <li>Orders up to UAH 300 are sent only on terms of 100% prepayment.</li>
                    <li>Some groups of goods are shipped with partial prepayment only.</li>
                    <li>The order and cost of delivery of large-sized goods is carried out in agreement with the managers of the online store. Delivery of bulky goods is carried out after 100% prepayment.</li>
                    <li>The order and cost of delivery of bulk orders is negotiated individually.</li>
                </ul>
            </div>
            <div class="a_link">
                <h2>Can't Find the Answer to Your Question?</h2>
                <a class="button" href="http://localhost:8080/final/server/contacts">Contact Us</a>
            </div>
        </div>
        <jsp:include page="footer.jsp"/>
    </div>
</body>
</html>