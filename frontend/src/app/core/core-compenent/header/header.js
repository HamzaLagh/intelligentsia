

$(document).ready(function () {
    var url_current=window.location.href;
    var item=url_current.split('/')[3] 
    var slides = document.getElementsByClassName("item-menu");
    for(var i = 0; i < slides.length; i++)
    {
        slides[i].classList.remove("active");
    }
    document.getElementById("menu-"+item).classList.add("active");

    
  });