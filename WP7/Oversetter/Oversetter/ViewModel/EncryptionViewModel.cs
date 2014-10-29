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
using System.Text;
using System.ComponentModel;

namespace Oversetter.ViewModel
{
    public class EncryptionViewModel : INotifyPropertyChanged
    {
        public char[] small = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'æ', 'ø', 'å' };
        public static List<char> Lookup = new List<char>();

        public EncryptionViewModel()
        {
            int i = 0;
            foreach(char s in small)
            {
                Lookup.Add(small[i]);
                i++;
            }
        }

        private string _TextToTranslate = "";
        public string TextToTranslate 
        {
            get
            {
                return _TextToTranslate;
            }
            set
            {
                _TextToTranslate = value;
                NotifyPropertyChanged("TextToTranslate");
                NotifyPropertyChanged("TranslatedText");
            }
        }

        public string TranslatedText
        {
            get
            {
                StringBuilder sb = new StringBuilder();

                foreach (char c in _TextToTranslate.ToLower())
                {
                    if (Lookup.Contains(c))
                    {
                        int i = Lookup.IndexOf(c);
                        int x = (small.Length - 1) - i;
                        sb.Append(Lookup[x]);
                    }
                    else
                    {
                        sb.Append(c);
                    }
                }
                return sb.ToString();
            }
        }

        public event PropertyChangedEventHandler PropertyChanged;
        private void NotifyPropertyChanged(String propertyName)
        {
            PropertyChangedEventHandler handler = PropertyChanged;
            if (null != handler)
            {
                handler(this, new PropertyChangedEventArgs(propertyName));
            }
        }
    }
}
