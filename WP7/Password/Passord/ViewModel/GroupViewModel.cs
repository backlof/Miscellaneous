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

namespace Passord.ViewModel
{
    public class GroupViewModel
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

        private List<EntryViewModel> _Entries = new List<EntryViewModel>();
        public List<EntryViewModel> Entries
        {
            get
            {
                return _Entries;
            }
            set
            {
                Entries = value;
            }
        }
    }
}
