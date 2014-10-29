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
using System.Security.Cryptography;
using System.Text;

namespace Passord.ViewModel
{
    public class DatabaseViewModel
    {
        private string _Name = "";
        public string Name
        {
            get
            {
                return _Name;
            }
            set
            {
                _Name = value;
            }
        }

        private string _Password = "";
        public string Password
        {
            get
            {
                return _Password;
            }
            set
            {
                var hash = new SHA256Managed();
                byte[] bytes = System.Text.Encoding.Unicode.GetBytes(value);
                byte[] encrypted = hash.ComputeHash(bytes);

                StringBuilder sb = new StringBuilder();
                int length = encrypted.Length;

                for (int i = 0; i < length; i++)
                {
                    sb.Append(encrypted[i].ToString("X2"));
                }

                _Password = sb.ToString();
            }
        }

        private List<GroupViewModel> _Groups = new List<GroupViewModel>();
        public List<GroupViewModel> Groups
        {
            get
            {
                //hente fra fil i isolated storage
                return _Groups;
            }
            set
            {
                _Groups = value;
            }
        }
    }
}
