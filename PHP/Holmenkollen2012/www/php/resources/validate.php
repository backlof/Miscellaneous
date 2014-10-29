<?php

$GLOBALS['properly_filled_in'] = true;

function validateEmail()
{
    if(isset($_POST['email']))
    {
        if($_POST['email'] == "test")
        {
            return "test er ok";
        }
        else if($_POST['email'] == "")
        {
            $GLOBALS['properly_filled_in'] = false;
            return 'må fylles inn';
        }
        else if (!preg_match('/^[^0-9][a-zA-Z0-9_]+([.][a-zA-Z0-9_]+)*[@][a-zA-Z0-9_]+([.][a-zA-Z0-9_]+)*[.][a-zA-Z]{2,4}$/', $_POST['email']))
        {
            $GLOBALS['properly_filled_in'] = false;
            return 'feil format på e-mail';
        }
    }
    else
    {
        $GLOBALS['properly_filled_in'] = false;
        return '*';   
    }
}

function validatePassword()
{
    if(isset($_POST['password']))
    {
        if($_POST['password'] == "")
        {
            $GLOBALS['properly_filled_in'] = false;
            return 'må fylles inn';
        }
        else if(strlen($_POST['password']) < 8)
        {
            $GLOBALS['properly_filled_in'] = false;
            return 'passordet er for kort';
        }
    }
    else
    {
        $GLOBALS['properly_filled_in'] = false;
        return '*';
    }
}

function validateAdress()
{
    if(isset($_POST['adress']))
    {
        if($_POST['adress'] == "")
        {
            $GLOBALS['properly_filled_in'] = false;
            return 'må fylles inn';
        }
        else if(!preg_match('/.{8,40}/', $_POST['adress']))
        {
            $GLOBALS['properly_filled_in'] = false;
            return 'mellom 8 og 40 bokstaver og tall';
        }
    }
    else
    {
        $GLOBALS['properly_filled_in'] = false;
        return '*';
    }
}

function validateFirst()
{
    if(isset($_POST['first']))
    {
        if($_POST['first'] == "")
        {
            $GLOBALS['properly_filled_in'] = false;
            return 'må fylles inn';
        }
        else if(!preg_match('/[a-zA-Z]{2,40}/', $_POST['first']))
        {
            $GLOBALS['properly_filled_in'] = false;
            return 'mellom 2 og 40 bokstaver';
        }
    }
    else
    {
        $GLOBALS['properly_filled_in'] = false;
        return '*';
    }
}

function validateLast()
{
    if(isset($_POST['last']))
    {
        if($_POST['last'] == "")
        {
            $GLOBALS['properly_filled_in'] = false;
            return 'må fylles inn';
        }
        else if(!preg_match('/[a-zA-Z]{2,40}/', $_POST['last']))
        {
            $GLOBALS['properly_filled_in'] = false;
            return 'mellom 2 og 40 bokstaver';
        }
    }
    else
    {
        $GLOBALS['properly_filled_in'] = false;
        return '*';
    }
}

function validateNationality()
{
    if(!isset($_POST['last']))
    {
        $GLOBALS['properly_filled_in'] = false;
        return '*';
    }
}

function validatePhone()
{
    if(isset($_POST['last']))
    {
        if($_POST['last'] == "")
        {
            $GLOBALS['properly_filled_in'] = false;
            return 'må fylles inn';
        }
        else if(!preg_match('/\d{8,14}/', $_POST['phone']))
        {
            $GLOBALS['properly_filled_in'] = false;
            return 'mellom 8 og 14 tall';
        }
    }
    else
    {
        $GLOBALS['properly_filled_in'] = false;
        return '*';
    }
}

?>