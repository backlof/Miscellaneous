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
using System.IO.IsolatedStorage;
using PasswordGenerator.ViewModel;

namespace PasswordGenerator.Controller
{
    public static class StorageController
    {
        private static string _Location = "data";
        public static int Launched = 0;
        public static int Activated = 0;

        public static void Load()
        {
            IsolatedStorageSettings settings = IsolatedStorageSettings.ApplicationSettings;

            if (settings.Contains(_Location))
            {
                StorageWrapper storageWrapper = settings[_Location] as StorageWrapper;

                App.PasswordViewModel = storageWrapper.PasswordViewModel;
                Launched = storageWrapper.Launched;
                Activated = storageWrapper.Activated;
            }
            else
            {
                App.PasswordViewModel = new PasswordViewModel();
            }
        }

        public static void Save()
        {
            IsolatedStorageSettings settings = IsolatedStorageSettings.ApplicationSettings;
            StorageWrapper storageWrapper = new StorageWrapper();

            if (settings.Contains(_Location))
            {
                settings[_Location] = storageWrapper;
            }
            else
            {
                settings.Add(_Location, storageWrapper);
            }

            try
            {
                settings.Save();
            }
            catch (Exception e)
            {
                MessageBox.Show("error");
            }
            MessageBox.Show("saved");
        }

    }

    public class StorageWrapper
    {
        public StorageWrapper()
        {
            PasswordViewModel = App.PasswordViewModel;
            Launched = StorageController.Launched;
            Activated = StorageController.Activated;
        }

        public PasswordViewModel PasswordViewModel { get; set; }
        public int Launched { get; set; }
        public int Activated { get; set; }
    }
}
