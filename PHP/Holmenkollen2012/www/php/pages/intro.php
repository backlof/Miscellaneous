<script src="js/slide.js"></script>
<script type="text/javascript" src="http://malsup.github.com/jquery.cycle.all.js"></script>

<div id="slideshow" class="slide">
    <img src="images/slide1.jpg" />
    <img src="images/slide2.jpg" class="hidden" />
    <img src="images/slide3.jpg" class="hidden" />
</div>





<h3>Velkommen til Holmenkollen 2012</h3>

<p>
    Den verdensomfattende FIS World Cup  i hopp (menn og kvinner), kombinert og langrenn er et årlig arrangement, koordinert av det Internationale Ski Forbundet (FIS) siden ca. 1980s. 
    Hver sesong består av ca. 25 - 30 arrangementer hver i de tre disiplinene, hver helg på en forskjellig arena. Hver helg består vanligvis av to konkurranser.
</p>
<p>
    World Cup Nordisk 2012 i Holmenkollen gjennomføres i Oslo 9 - 11. mars 2012, og er en av tre årlige helger der alle disipliner er samlet.
</p>
<h4>mest populære</h4>
<div id="events">
    <?php
    $sql = 'SELECT EVENT.ID AS id, TYPE.TYPE AS type, ISMALES AS ismales FROM EVENT INNER JOIN TYPE ON (TYPE.ID = TYPE_ID) LIMIT 3;';
    $query = mysql_query($sql) or die(mysql_error());
    if(mysql_affected_rows() > 0 )
    {
        while($res = mysql_fetch_array($query))
        {
            $event = new Event($res);
            echo '<div class="event">';
            echo '<a href="?id='.$event->id.'">';
            echo '<img src="images/events/'.$event->id.'.jpg" />';
            echo '<p class="eventText">'.$event->type.' '.$event->gender.'</p>';
            echo '</a>';
            echo '</div>';
        }
    }
    ?>
</div>