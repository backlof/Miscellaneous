using System;
using System.Net;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Documents;
using System.Windows.Ink;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Animation;
using System.Windows.Shapes;
using System.Collections.Generic;
using System.IO.IsolatedStorage;
using System.Security.Cryptography;
using System.IO;
using System.Text;

namespace Passord.Controller
{
    public static class StorageController
    {
        static 
        static List<string> _Databases;

        public static void Load()
        {
            IsolatedStorageSettings settings = IsolatedStorageSettings.ApplicationSettings;

            if (settings.Contains("databases"))
            {
                _Databases = settings["databases"] as List<string>;
            }
            else
            {
                _Databases = new List<string>();
                _Databases.Add("default");
            }
        }



        public static void Save()
        {
        }
        //metoder for innhenting
        //metoder for passordsjekking
    }
}


