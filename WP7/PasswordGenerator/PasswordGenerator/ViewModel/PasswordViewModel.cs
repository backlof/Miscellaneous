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
using System.ComponentModel;
using PasswordGenerator.Utility;
using WP7MangoDelegateCommand;

namespace PasswordGenerator.ViewModel
{
    public class PasswordViewModel : ViewModelBase
    {
        #region Properties

        private string _Password = "";
        public string Password
        {
            get
            {
                return _Password;
            }
            set
            {
                _Password = value;
                onPropertyChanged("Password");
            }
        }

        private bool _Memorable = false;
        public bool Memorable
        {
            get
            {
                return _Memorable;
            }
            set
            {
                _Memorable = value;
            }
        }

        #endregion

        #region Memorable Properties
        #endregion

        #region Random Properties

        private bool _UpperCases = true;
        public bool UpperCases
        {
            get
            {
                return _UpperCases;
            }
            set
            {
                _UpperCases = value;
            }
        }
        public string UpperCasesText = "Upper case (A, B, C, ...)";

        private bool _LowerCases = true;
        public bool LowerCases
        {
            get
            {
                return _LowerCases;
            }
            set
            {
                _LowerCases = value;
            }
        }
        public string LowerCasesText = "Lower case (a, b, c, ...)";

        private bool _Digits = true;
        public bool Digits
        {
            get
            {
                return _Digits;
            }
            set
            {
                _Digits = value;
            }
        }
        public string DigitsText = "Digits (0, 1, 2, ...)";

        private bool _Spaces = false;
        public bool Space
        {
            get
            {
                return _Spaces;
            }
            set
            {
                _Spaces = value;
            }
        }
        public string SpacesText = "Spaces (  )";

        private bool _Specials = false;
        public bool Specials
        {
            get
            {
                return _Specials;
            }
            set
            {
                _Specials = value;
            }
        }
        public string SpecialsText = "Special symbols ($, &, %, !, ...)";

        private bool _Brackets = false;
        public bool Brackets
        {
            get
            {
                return _Brackets;
            }
            set
            {
                _Brackets = value;
            }
        }
        public string BracketsText = "Brackets ([, ], {, }, ..)";


        private int _Length = 12;
        public int Length
        {
            get
            {
                return _Length;
            }
            set
            {
                _Length = value;
            }
        }

        

        #endregion

        public ICommand Test
        {
            get
            {
                return new DelegateCommand(GeneratePassword);
            }
        }

        private void GeneratePassword(object p)
        {
            Password = "test";
            /*
            if (Memorable)
            {
                //generer minneverdig passord
            }
            else
            {
                //generer passord etter 
            }
             */
        }
    }
}
