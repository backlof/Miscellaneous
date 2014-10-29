<h3>Program</h3>
<?php
$sql = 'SELECT DATE AS date, EVENT.ID AS id, TYPE.TYPE AS type, ISMALES AS ismales FROM EVENT INNER JOIN TYPE ON (TYPE.ID = TYPE_ID) ORDER BY date;';
$query = mysql_query($sql) or die(mysql_error());
if(mysql_affected_rows() > 0 )
{
    while($res = mysql_fetch_array($query))
    {
        $event = new Event($res);
        echo '<p>'.$event->date.'</p>';
        echo '<p class="program"><a href="?id='.           $event->id  .   '">'  .$event->type.' '.$event->gender.'</a></p>';
    }
}
?>