using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.IO;
namespace Histograma_Local
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        private void openFileDialog1_FileOk(object sender, CancelEventArgs e)
        {
           
        }

        private void btnBrowse_Click(object sender, EventArgs e)
        {
           

            int size = -1;
            DialogResult result = openFileDialog1.ShowDialog(); 
            if (result == DialogResult.OK) 
            {

                try
                {
                    string file = openFileDialog1.FileName;

                    Image img = Image.FromFile(file);
                    Bitmap bm = new Bitmap(img);


                    pbxOriginal.Image = (Image)bm;

                }
                catch (Exception)
                {

                }

                
               
            }
         
        }

        private void btnExecuteGray_Click(object sender, EventArgs e)
        {
            if (pbxOriginal.Image == null) return;


            chartGray.Visible = true;
            chartGray.Series.Clear();
            chartGray.Series.Add("Pixels");
            pbxGray.Image = Algorithms.ConvertToGrayscale(pbxOriginal.Image);
            Dictionary<int, int> histogramDictionary = Algorithms.GetHistogramData(pbxGray.Image);

            foreach(int key in histogramDictionary.Keys)
            {
                int value = histogramDictionary[key];
                chartGray.Series["Pixels"].Points.AddXY(key.ToString(),value.ToString());

            }

            
        }

        private void chartGray_Click(object sender, EventArgs e)
        {

        }

        private void btnExecuteNoise_Click(object sender, EventArgs e)
        {
            if (pbxGray.Image == null) return;

            chartNoise.Visible = true;
            chartNoise.Series.Clear();
            chartNoise.Series.Add("Pixels");


            float trackValue = (float)((double)trackBarNoise.Value/100f);

            int pixelsToChange =  (int)((pbxGray.Image.Height * pbxGray.Image.Width) * trackValue);

            pbxNoise.Image = Algorithms.AddRandomNoiseToImage(pbxGray.Image, pixelsToChange);

            Dictionary<int, int> histogramDictionary = Algorithms.GetHistogramData(pbxNoise.Image);

            foreach (int key in histogramDictionary.Keys)
            {
                int value = histogramDictionary[key];
                chartNoise.Series["Pixels"].Points.AddXY(key.ToString(), value.ToString());

            }
        }

        private void btnExecuteLocal_Click(object sender, EventArgs e)
        {
            chartBordes.Visible = true;
            chartBordes.Series.Clear();
            chartBordes.Series.Add("Pixels");
            pbxBorde.Image = Algorithms.LocalHistogramEqualization(pbxLocal.Image, 3, 3);
            Dictionary<int, int> histogramDictionary = Algorithms.GetHistogramData(pbxBorde.Image);

            foreach (int key in histogramDictionary.Keys)
            {
                int value = histogramDictionary[key];
                chartBordes.Series["Pixels"].Points.AddXY(key.ToString(), value.ToString());

            }
        }

        //private void btnExecuteGlobal_Click(object sender, EventArgs e)
        //{
        //    chartLocal.Visible = true;
        //    chartLocal.Series.Clear();
        //    chartLocal.Series.Add("Pixels");
        //    pbxLocal.Image = Algorithms.GlobalEqualization(pbxNoise.Image);
        //    Dictionary<int, int> histogramDictionary = Algorithms.GetHistogramData(pbxLocal.Image);

        //    foreach (int key in histogramDictionary.Keys)
        //    {
        //        int value = histogramDictionary[key];
        //        chartLocal.Series["Pixels"].Points.AddXY(key.ToString(), value.ToString());

        //    }
        //}

        private void saveFileDialog1_FileOk(object sender, CancelEventArgs e)
        {

        }

        private void btnSaveGray_Click(object sender, EventArgs e)
        {
            if (pbxGray.Image == null) return;

            DialogResult result = saveFileDialog1.ShowDialog(); 
            if (result == DialogResult.OK)
            {

                try
                {
                    string file = saveFileDialog1.FileName;
                    pbxGray.Image.Save(file + ".png");

                }
                catch (IOException)
                {
                }

            }
            
        }

        private void btnSaveNoise_Click(object sender, EventArgs e)
        {
            if (pbxNoise.Image == null) return;
            DialogResult result = saveFileDialog1.ShowDialog();
            if (result == DialogResult.OK)
            {

                try
                {
                    string file = saveFileDialog1.FileName;
                    pbxNoise.Image.Save(file + ".png");

                }
                catch (IOException)
                {
                }

            }
        }

        private void btnSaveLocal_Click(object sender, EventArgs e)
        {
            if (pbxLocal.Image == null) return;

            DialogResult result = saveFileDialog1.ShowDialog();
            if (result == DialogResult.OK)
            {

                try
                {
                    string file = saveFileDialog1.FileName;
                    pbxLocal.Image.Save(file + ".png");

                }
                catch (IOException)
                {
                }

            }
        }

        private void btnSaveBorde_Click(object sender, EventArgs e)
        {
            if (pbxBorde.Image == null) return;

            DialogResult result = saveFileDialog1.ShowDialog();
            if (result == DialogResult.OK)
            {

                try
                {
                    string file = saveFileDialog1.FileName;
                    pbxBorde.Image.Save(file + ".png");

                }
                catch (IOException)
                {
                }

            }
        }

        private void Form1_Load(object sender, EventArgs e)
        {
            chartLocal.Visible = false;
            chartNoise.Visible = false;
            chartGray.Visible = false;
            trackBarNoise.SetRange(0, 100);

        }

        private void btnMedian_Click(object sender, EventArgs e)
        {
            chartLocal.Visible = true;
            chartLocal.Series.Clear();
            chartLocal.Series.Add("Pixels");
            pbxLocal.Image = Algorithms.MedianEqualization(pbxNoise.Image);
            Dictionary<int, int> histogramDictionary = Algorithms.GetHistogramData(pbxLocal.Image);

            foreach (int key in histogramDictionary.Keys)
            {
                int value = histogramDictionary[key];
                chartLocal.Series["Pixels"].Points.AddXY(key.ToString(), value.ToString());
            }

        }

        
    }
}
