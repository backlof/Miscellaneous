<?php
session_start();

include 'php/resources/path.php';
include RESOURCES_PATH.'mysql.php';
include RESOURCES_PATH.'classes.php';
include RESOURCES_PATH.'functions.php';

if(isset($_GET['action']))
{
    if($_GET['action'] == 'logout')
    {
        unset($_SESSION['person_id']);
    }
}

//sjekke session variabel opp mot database
//session variabel: tid + passord i md5
?>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>VM i Holmenkollen</title>
        <link rel="stylesheet" type="text/css" href="css/style.css" />
        <!--[if IE]>
                <script src="http://html5shiv.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->
        
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.2/jquery.min.js"></script>
        <script src="js/timeout.js"></script>

    </head>

    <body>
        <header>
            <div class="wrap">
                <h1><a href="?">VM i Holmenkollen 2012</a></h1>
                <!--Skal defineres av om man er logget inn eller ikke -->
                <p class="nav" id="program"><a href="?page=program">Program</a></p>
                <?php
                if(isset($_SESSION["person_id"]))
                {
                    echo '<p class"nav" id="login"><a href="?action=logout">> Logg ut</a></p>';
                }
                else
                {
                    echo '<p class"nav" id="login"><a href="?page=login">> Logg inn</a></p>';
                }
                ?>
            </div>
        </header>
        <div class="wrap">
            <section>
                <?php
                    if(isset($_GET['page']))
                    {
                        if(file_exists(PAGES_PATH.$_GET['page'].'.php'))
                        {
                            include PAGES_PATH.$_GET['page'].'.php';
                        }
                        else
                        {
                            include PAGES_PATH.'404.php';
                        }
                    }
                    else
                    {
                        if(isset($_GET['id']))
                        {
                            include PAGES_PATH.'single.php';
                        }
                        else
                        {
                            include PAGES_PATH.'intro.php';
                        }
                    }
                ?>
            </section>
            <aside>
                <form action="<?php echo CONTROLLERS_PATH."submit_event.php"; ?>" method="POST">
                    <input type="text" name="phone" id="searchInput" />
                    <input type="submit" id="searchButton" class="button" value="søk" />
                </form>
                <div id="sidebar">
                    <p id="sidebarText">
                        Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod
                        tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam,
                        quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo
                        consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse
                        cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non
                        proident, sunt in culpa qui officia deserunt mollit anim id est laborum.
                    </p>
                </div>
            </aside>
        </div>
    </body>
</html>