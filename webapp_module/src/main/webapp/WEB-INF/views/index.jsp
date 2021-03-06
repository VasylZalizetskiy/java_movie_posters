<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <title>MoviePosters</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="stylesheet" href="/resources/css/style.css" type="text/css" media="all" />
    <script type="text/javascript" src="/resources/js/jquery-1.4.2.min.js"></script>
    <script type="text/javascript" src="/resources/js/jquery-func.js"></script>
    <script type="text/javascript" src="/resources/js/modal.js"></script>
    <!--[if IE 6]><link rel="stylesheet" href="/resources/css/ie6.css" type="text/css" media="all" /><![endif]-->
</head>
<body>
<!-- START PAGE SOURCE -->

<div id="shell">
    <!-- The Modal -->
    <div id="id01" class="modal">

        <form class="modal-content animate" action="/" method="post" enctype="application/json">
            <div class="imgcontainer">
                <span onclick="document.getElementById('id01').style.display='none'" class="close" title="Close Modal">&times;</span>
                <img src="/resources/images/plus.jpg" alt="Avatar" class="avatar">
            </div>

            <div class="container">
                <label for="name"><b>Movie Name</b></label>
                <input type="text" placeholder="Movie Name" name="name" required>

                <label for="year"><b>Released Year</b></label>
                <input type="text" placeholder="year" name="year" required>

                <label for="image"><b>Image</b></label>
                <input type="text" placeholder="image" name="image" required>

                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

                <button type="submit">Save</button>
            </div>

            <div class="container" style="background-color:#f1f1f1">
                <button type="button" onclick="document.getElementById('id01').style.display='none'" class="cancelbtn">Cancel</button>
            </div>
        </form>
    </div>
    <div id="header">
        <h1 id="logo"><a href="/">MoviePosters</a></h1>
        <div class="social"> <span>FOLLOW US ON:</span>
            <ul>
                <li><a class="twitter" href="#">twitter</a></li>
                <li><a class="facebook" href="#">facebook</a></li>
                <li><a class="vimeo" href="#">vimeo</a></li>
                <li><a class="rss" href="#">rss</a></li>
            </ul>
        </div>
        <div id="navigation">
            <c:choose>
                <c:when test="${not empty pageContext.request.remoteUser}">
                    <form action="/logout" method="post">
                        <ul>
                            <li><label><h1>Hello <b><c:out value="${pageContext.request.remoteUser}"></c:out></b></h1></label></li>
                            <li><input class="cancelbtn" type="submit" value="Sign Out" /></li>
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
                        </ul>
                    </form>
                </c:when>
                <c:otherwise>
                    <ul>
                        <li><a href="/login">Login</a></li>
                    </ul>
                </c:otherwise>
            </c:choose>
        </div>
        <div id="sub-navigation">
            <ul>
                <li><a href="/">All</a></li>
                <li><a href="/?year=1997">1997</a></li>
                <li><a href="/?year=2002">2002</a></li>
                <li><a href="/?year=2003">2003</a></li>
                <li><a href="/?year=2008">2008</a></li>
                <li><a href="/?year=2018">2018</a></li>
            </ul>
            <div id="search">
                <form action="#" method="get" accept-charset="utf-8">
                    <label for="search-field">SEARCH</label>
                    <input type="text" name="search field" value="Enter search here" id="search-field" class="blink search-field"  />
                    <input type="submit" value="GO!" class="search-button" />
                </form>
            </div>
        </div>
    </div>
    <div id="main">
        <div id="content">
            <div class="box">
                <div class="head">
                    <h2>LATEST TRAILERS</h2>
                    <p class="text-right"><a href="#">See all</a></p>
                </div>


<c:forEach items="${movieList}" var="item">
                <div class="movie">
                    <h1>${item.name}</h1>
                    <c:choose>
                        <c:when test="${not empty pageContext.request.remoteUser}">
                    <a href="/remove/?name=${item.name}"> <div class="movie-image"> <span class="remove"><span class="name">${item.name}</span></span> <img src="/resources/images/${item.image}.jpg" alt="" /></div></a>
                        </c:when>
                        <c:otherwise>
                    <a href="#"><div class="movie-image"> <span class="play"><span class="name">${item.name}</span></span> <img src="/resources/images/${item.image}.jpg" alt="" /></div></a>
                        </c:otherwise>
                    </c:choose>
                    <div class="rating">
                        <p>RATING</p>
                        <div class="stars">
                            <div class="stars-in"> </div>
                        </div>
                    </div>
                    <div>
                        <p>YEAR <span class="year">${item.year}</span></p>
                    </div>
                </div>
</c:forEach>
                <div class="movie">
                    <h1>New movie</h1>
                    <div class="movie-image"><a onclick="document.getElementById('id01').style.display='block'"><img src="/resources/images/plus.jpg" alt="" /></a> </div>
                </div>
                <div class="cl">&nbsp;</div>
            </div>
        </div>
        <div id="news">
            <div class="head">
                <h3>NEWS</h3>
                <p class="text-right"><a href="#">See all</a></p>
            </div>
            <div class="content">
                <p class="date">12.04.09</p>
                <h4>Disney's A Christmas Carol</h4>
                <p>&quot;Disney's A Christmas Carol,&quot; a multi-sensory thrill ride re-envisioned by Academy Award&reg;-winning filmmaker Robert Zemeckis, captures... </p>
                <a href="#">Read more</a> </div>
            <div class="content">
                <p class="date">11.04.09</p>
                <h4>Where the Wild Things Are</h4>
                <p>Innovative director Spike Jonze collaborates with celebrated author Maurice Sendak to bring one of the most beloved books of all time to the big screen in &quot;Where the Wild Things Are,&quot;...</p>
                <a href="#">Read more</a> </div>
            <div class="content">
                <p class="date">10.04.09</p>
                <h4>The Box</h4>
                <p>Norma and Arthur Lewis are a suburban couple with a young child who receive an anonymous gift bearing fatal and irrevocable consequences.</p>
                <a href="#">Read more</a> </div>
        </div>
        <div id="coming">
            <div class="head">
                <h3>COMING SOON<strong>!</strong></h3>
                <p class="text-right"><a href="#">See all</a></p>
            </div>
            <div class="content">
                <h4>The Princess and the Frog </h4>
                <a href="#"><img src="/resources/images/coming-soon1.jpg" alt="" /></a>
                <p>Walt Disney Animation Studios presents the musical &quot;The Princess and the Frog,&quot; an animated comedy set in the great city of New Orleans...</p>
                <a href="#">Read more</a> </div>
            <div class="cl">&nbsp;</div>
            <div class="content">
                <h4>The Princess and the Frog </h4>
                <a href="#"><img src="/resources/images/coming-soon2.jpg" alt="" /></a>
                <p>Walt Disney Animation Studios presents the musical &quot;The Princess and the Frog,&quot; an animated comedy set in the great city of New Orleans...</p>
                <a href="#">Read more</a> </div>
        </div>
        <div class="cl">&nbsp;</div>
    </div>
    <div id="footer">
        <p class="lf">Copyright &copy; 2018 <a href="#">Simple JSP</a> - All Rights Reserved</p>
        <p class="rf">Design by <a href="#">Simple JSP</a></p>
        <div style="clear:both;"></div>
    </div>
</div>
<!-- END PAGE SOURCE -->
</body>
</html>