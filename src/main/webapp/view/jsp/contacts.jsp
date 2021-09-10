<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link href="https://fonts.googleapis.com/css2?family=Varela+Round&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="http://localhost:8080/final/view/css/main.css">
    <link rel="stylesheet" href="http://localhost:8080/final/view/css/contacts.css">
    <script src="../main.js"></script>
    <title>Contacts</title>
</head>
<body>
    <jsp:include page="header.jsp"/>
    <div class="body">
        <div class="blank"></div>
        <div class="navigation">
            <a href="http://localhost:8080/final/server/home">Home</a>
            <img src="http://localhost:8080/final/view/media/5f8f84f3d4d23a31c1f2fcae_arrow-right-mini-icon.svg">
            Contacts
        </div>
        <div class="all">
            <iframe class="a_maps" src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2540.98861737099!2d30.502796915731007!3d50.441312679474144!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x40d4cef08e0df497%3A0xc6f4c41b38d10a5c!2z0LLRg9C70LjRhtGPINCb0YzQstCwINCi0L7Qu9GB0YLQvtCz0L4sINCa0LjRl9CyLCAwMjAwMA!5e0!3m2!1suk!2sua!4v1606785124053!5m2!1suk!2sua" ></iframe>
            <div class="a_info">
                <form class="a_i_form">
                    <h1>Leave a Message</h1>
                    <label>Full Name</label>
                    <input class="a_i_f_text" type="text" placeholder="Enter your name">
                    <label>Email Address</label>
                    <input class="a_i_f_text" type="email" placeholder="Your contact email">
                    <label>Message</label>
                    <textarea class="a_i_f_area" placeholder="Message text"></textarea>
                    <button type="submit">Send Message</button>
                </form>
                <div class="a_i_info">
                    <h1>Contact Info</h1>
                    01001 Kyiv, Khreshchatyk 101a<br>
                    +044 123-78-93
                    <div class="b">conststore@gmail.com</div>
                    <h2>Follow Us</h2>
                    <div class="a_i_i_socials">
                        <a class="twitter" href=""><img src="http://localhost:8080/final/view/media/social/twitter.svg"></a>
                        <a class="facebook" href=""><img src="http://localhost:8080/final/view/media/social/facebook.svg"></a>
                        <a class="instagram" href=""><img src="http://localhost:8080/final/view/media/social/instagram.svg"></a>
                        <a class="pintrest" href=""><img src="http://localhost:8080/final/view/media/social/pinterest.svg"></a>
                        <a class="youtube" href=""><img src="http://localhost:8080/final/view/media/social/youtube.svg"></a>
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="footer.jsp"/>
    </div>
</body>
</html>