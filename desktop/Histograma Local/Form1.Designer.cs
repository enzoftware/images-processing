namespace Histograma_Local
{
    partial class Form1
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            System.Windows.Forms.DataVisualization.Charting.ChartArea chartArea1 = new System.Windows.Forms.DataVisualization.Charting.ChartArea();
            System.Windows.Forms.DataVisualization.Charting.Legend legend1 = new System.Windows.Forms.DataVisualization.Charting.Legend();
            System.Windows.Forms.DataVisualization.Charting.Series series1 = new System.Windows.Forms.DataVisualization.Charting.Series();
            System.Windows.Forms.DataVisualization.Charting.ChartArea chartArea2 = new System.Windows.Forms.DataVisualization.Charting.ChartArea();
            System.Windows.Forms.DataVisualization.Charting.Legend legend2 = new System.Windows.Forms.DataVisualization.Charting.Legend();
            System.Windows.Forms.DataVisualization.Charting.Series series2 = new System.Windows.Forms.DataVisualization.Charting.Series();
            System.Windows.Forms.DataVisualization.Charting.ChartArea chartArea3 = new System.Windows.Forms.DataVisualization.Charting.ChartArea();
            System.Windows.Forms.DataVisualization.Charting.Legend legend3 = new System.Windows.Forms.DataVisualization.Charting.Legend();
            System.Windows.Forms.DataVisualization.Charting.Series series3 = new System.Windows.Forms.DataVisualization.Charting.Series();
            System.Windows.Forms.DataVisualization.Charting.ChartArea chartArea4 = new System.Windows.Forms.DataVisualization.Charting.ChartArea();
            System.Windows.Forms.DataVisualization.Charting.Legend legend4 = new System.Windows.Forms.DataVisualization.Charting.Legend();
            System.Windows.Forms.DataVisualization.Charting.Series series4 = new System.Windows.Forms.DataVisualization.Charting.Series();
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(Form1));
            this.openFileDialog1 = new System.Windows.Forms.OpenFileDialog();
            this.chartGray = new System.Windows.Forms.DataVisualization.Charting.Chart();
            this.chartNoise = new System.Windows.Forms.DataVisualization.Charting.Chart();
            this.chartLocal = new System.Windows.Forms.DataVisualization.Charting.Chart();
            this.saveFileDialog1 = new System.Windows.Forms.SaveFileDialog();
            this.btnExecuteLocal = new System.Windows.Forms.Button();
            this.btnExecuteNoise = new System.Windows.Forms.Button();
            this.btnExecuteGray = new System.Windows.Forms.Button();
            this.btnSaveLocal = new System.Windows.Forms.Button();
            this.btnSaveNoise = new System.Windows.Forms.Button();
            this.btnSaveGray = new System.Windows.Forms.Button();
            this.pbxLocal = new System.Windows.Forms.PictureBox();
            this.pbxNoise = new System.Windows.Forms.PictureBox();
            this.pbxGray = new System.Windows.Forms.PictureBox();
            this.pbxOriginal = new System.Windows.Forms.PictureBox();
            this.btnBrowse = new System.Windows.Forms.Button();
            this.label1 = new System.Windows.Forms.Label();
            this.label2 = new System.Windows.Forms.Label();
            this.label3 = new System.Windows.Forms.Label();
            this.label4 = new System.Windows.Forms.Label();
            this.trackBarNoise = new System.Windows.Forms.TrackBar();
            this.label11 = new System.Windows.Forms.Label();
            this.label12 = new System.Windows.Forms.Label();
            this.btnMedian = new System.Windows.Forms.Button();
            this.pbxBorde = new System.Windows.Forms.PictureBox();
            this.label5 = new System.Windows.Forms.Label();
            this.chartBordes = new System.Windows.Forms.DataVisualization.Charting.Chart();
            this.btnSaveBorde = new System.Windows.Forms.Button();
            ((System.ComponentModel.ISupportInitialize)(this.chartGray)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.chartNoise)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.chartLocal)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.pbxLocal)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.pbxNoise)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.pbxGray)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.pbxOriginal)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.trackBarNoise)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.pbxBorde)).BeginInit();
            ((System.ComponentModel.ISupportInitialize)(this.chartBordes)).BeginInit();
            this.SuspendLayout();
            // 
            // openFileDialog1
            // 
            this.openFileDialog1.FileName = "openFileDialog1";
            this.openFileDialog1.FileOk += new System.ComponentModel.CancelEventHandler(this.openFileDialog1_FileOk);
            // 
            // chartGray
            // 
            chartArea1.Name = "ChartArea1";
            this.chartGray.ChartAreas.Add(chartArea1);
            legend1.Name = "Legend1";
            this.chartGray.Legends.Add(legend1);
            this.chartGray.Location = new System.Drawing.Point(220, 370);
            this.chartGray.Name = "chartGray";
            this.chartGray.Palette = System.Windows.Forms.DataVisualization.Charting.ChartColorPalette.SeaGreen;
            series1.ChartArea = "ChartArea1";
            series1.Legend = "Legend1";
            series1.Name = "Pixels";
            this.chartGray.Series.Add(series1);
            this.chartGray.Size = new System.Drawing.Size(202, 136);
            this.chartGray.TabIndex = 12;
            this.chartGray.Text = "Original";
            this.chartGray.Click += new System.EventHandler(this.chartGray_Click);
            // 
            // chartNoise
            // 
            chartArea2.Name = "ChartArea1";
            this.chartNoise.ChartAreas.Add(chartArea2);
            legend2.Name = "Legend1";
            this.chartNoise.Legends.Add(legend2);
            this.chartNoise.Location = new System.Drawing.Point(437, 370);
            this.chartNoise.Name = "chartNoise";
            this.chartNoise.Palette = System.Windows.Forms.DataVisualization.Charting.ChartColorPalette.SeaGreen;
            series2.ChartArea = "ChartArea1";
            series2.Legend = "Legend1";
            series2.Name = "Pixels";
            this.chartNoise.Series.Add(series2);
            this.chartNoise.Size = new System.Drawing.Size(202, 136);
            this.chartNoise.TabIndex = 13;
            this.chartNoise.Text = "Original";
            // 
            // chartLocal
            // 
            chartArea3.Name = "ChartArea1";
            this.chartLocal.ChartAreas.Add(chartArea3);
            legend3.Name = "Legend1";
            this.chartLocal.Legends.Add(legend3);
            this.chartLocal.Location = new System.Drawing.Point(636, 370);
            this.chartLocal.Name = "chartLocal";
            this.chartLocal.Palette = System.Windows.Forms.DataVisualization.Charting.ChartColorPalette.SeaGreen;
            series3.ChartArea = "ChartArea1";
            series3.Legend = "Legend1";
            series3.Name = "Pixels";
            this.chartLocal.Series.Add(series3);
            this.chartLocal.Size = new System.Drawing.Size(193, 136);
            this.chartLocal.TabIndex = 14;
            this.chartLocal.Text = "Original";
            // 
            // saveFileDialog1
            // 
            this.saveFileDialog1.FileOk += new System.ComponentModel.CancelEventHandler(this.saveFileDialog1_FileOk);
            // 
            // btnExecuteLocal
            // 
            this.btnExecuteLocal.Image = global::Histograma_Local.Properties.Resources._1448139329_image;
            this.btnExecuteLocal.ImageAlign = System.Drawing.ContentAlignment.MiddleRight;
            this.btnExecuteLocal.Location = new System.Drawing.Point(844, 273);
            this.btnExecuteLocal.Name = "btnExecuteLocal";
            this.btnExecuteLocal.Size = new System.Drawing.Size(86, 40);
            this.btnExecuteLocal.TabIndex = 10;
            this.btnExecuteLocal.Text = "Execute(Canny)";
            this.btnExecuteLocal.TextImageRelation = System.Windows.Forms.TextImageRelation.ImageBeforeText;
            this.btnExecuteLocal.UseVisualStyleBackColor = true;
            this.btnExecuteLocal.Click += new System.EventHandler(this.btnExecuteLocal_Click);
            // 
            // btnExecuteNoise
            // 
            this.btnExecuteNoise.Image = global::Histograma_Local.Properties.Resources._1448139329_image;
            this.btnExecuteNoise.ImageAlign = System.Drawing.ContentAlignment.MiddleRight;
            this.btnExecuteNoise.Location = new System.Drawing.Point(428, 273);
            this.btnExecuteNoise.Name = "btnExecuteNoise";
            this.btnExecuteNoise.Size = new System.Drawing.Size(86, 40);
            this.btnExecuteNoise.TabIndex = 9;
            this.btnExecuteNoise.Text = "Execute";
            this.btnExecuteNoise.TextImageRelation = System.Windows.Forms.TextImageRelation.ImageBeforeText;
            this.btnExecuteNoise.UseVisualStyleBackColor = true;
            this.btnExecuteNoise.Click += new System.EventHandler(this.btnExecuteNoise_Click);
            // 
            // btnExecuteGray
            // 
            this.btnExecuteGray.Image = global::Histograma_Local.Properties.Resources._1448139329_image;
            this.btnExecuteGray.ImageAlign = System.Drawing.ContentAlignment.MiddleRight;
            this.btnExecuteGray.Location = new System.Drawing.Point(220, 273);
            this.btnExecuteGray.Name = "btnExecuteGray";
            this.btnExecuteGray.Size = new System.Drawing.Size(86, 40);
            this.btnExecuteGray.TabIndex = 8;
            this.btnExecuteGray.Text = "Execute";
            this.btnExecuteGray.TextImageRelation = System.Windows.Forms.TextImageRelation.ImageBeforeText;
            this.btnExecuteGray.UseVisualStyleBackColor = true;
            this.btnExecuteGray.Click += new System.EventHandler(this.btnExecuteGray_Click);
            // 
            // btnSaveLocal
            // 
            this.btnSaveLocal.Image = global::Histograma_Local.Properties.Resources._1448138637_filesave;
            this.btnSaveLocal.ImageAlign = System.Drawing.ContentAlignment.MiddleRight;
            this.btnSaveLocal.Location = new System.Drawing.Point(728, 273);
            this.btnSaveLocal.Name = "btnSaveLocal";
            this.btnSaveLocal.Size = new System.Drawing.Size(110, 40);
            this.btnSaveLocal.TabIndex = 7;
            this.btnSaveLocal.Text = "Save";
            this.btnSaveLocal.UseVisualStyleBackColor = true;
            this.btnSaveLocal.Click += new System.EventHandler(this.btnSaveLocal_Click);
            // 
            // btnSaveNoise
            // 
            this.btnSaveNoise.Image = global::Histograma_Local.Properties.Resources._1448138637_filesave;
            this.btnSaveNoise.ImageAlign = System.Drawing.ContentAlignment.MiddleRight;
            this.btnSaveNoise.Location = new System.Drawing.Point(522, 273);
            this.btnSaveNoise.Name = "btnSaveNoise";
            this.btnSaveNoise.Size = new System.Drawing.Size(108, 40);
            this.btnSaveNoise.TabIndex = 6;
            this.btnSaveNoise.Text = "Save";
            this.btnSaveNoise.UseVisualStyleBackColor = true;
            this.btnSaveNoise.Click += new System.EventHandler(this.btnSaveNoise_Click);
            // 
            // btnSaveGray
            // 
            this.btnSaveGray.BackgroundImageLayout = System.Windows.Forms.ImageLayout.None;
            this.btnSaveGray.Image = global::Histograma_Local.Properties.Resources._1448138637_filesave;
            this.btnSaveGray.ImageAlign = System.Drawing.ContentAlignment.MiddleRight;
            this.btnSaveGray.Location = new System.Drawing.Point(314, 273);
            this.btnSaveGray.Name = "btnSaveGray";
            this.btnSaveGray.Size = new System.Drawing.Size(108, 40);
            this.btnSaveGray.TabIndex = 5;
            this.btnSaveGray.Text = "Save";
            this.btnSaveGray.UseVisualStyleBackColor = true;
            this.btnSaveGray.Click += new System.EventHandler(this.btnSaveGray_Click);
            // 
            // pbxLocal
            // 
            this.pbxLocal.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.pbxLocal.Location = new System.Drawing.Point(636, 74);
            this.pbxLocal.Name = "pbxLocal";
            this.pbxLocal.Size = new System.Drawing.Size(202, 193);
            this.pbxLocal.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage;
            this.pbxLocal.TabIndex = 4;
            this.pbxLocal.TabStop = false;
            // 
            // pbxNoise
            // 
            this.pbxNoise.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.pbxNoise.Location = new System.Drawing.Point(428, 74);
            this.pbxNoise.Name = "pbxNoise";
            this.pbxNoise.Size = new System.Drawing.Size(202, 193);
            this.pbxNoise.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage;
            this.pbxNoise.TabIndex = 3;
            this.pbxNoise.TabStop = false;
            // 
            // pbxGray
            // 
            this.pbxGray.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.pbxGray.Location = new System.Drawing.Point(220, 73);
            this.pbxGray.Name = "pbxGray";
            this.pbxGray.Size = new System.Drawing.Size(202, 194);
            this.pbxGray.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage;
            this.pbxGray.TabIndex = 2;
            this.pbxGray.TabStop = false;
            // 
            // pbxOriginal
            // 
            this.pbxOriginal.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.pbxOriginal.Location = new System.Drawing.Point(12, 73);
            this.pbxOriginal.Name = "pbxOriginal";
            this.pbxOriginal.Size = new System.Drawing.Size(202, 193);
            this.pbxOriginal.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage;
            this.pbxOriginal.TabIndex = 1;
            this.pbxOriginal.TabStop = false;
            // 
            // btnBrowse
            // 
            this.btnBrowse.Image = global::Histograma_Local.Properties.Resources._1448139488_folder;
            this.btnBrowse.ImageAlign = System.Drawing.ContentAlignment.MiddleRight;
            this.btnBrowse.Location = new System.Drawing.Point(12, 273);
            this.btnBrowse.Name = "btnBrowse";
            this.btnBrowse.Size = new System.Drawing.Size(108, 40);
            this.btnBrowse.TabIndex = 0;
            this.btnBrowse.Text = "Browse Files";
            this.btnBrowse.TextImageRelation = System.Windows.Forms.TextImageRelation.ImageBeforeText;
            this.btnBrowse.UseVisualStyleBackColor = true;
            this.btnBrowse.Click += new System.EventHandler(this.btnBrowse_Click);
            // 
            // label1
            // 
            this.label1.AutoSize = true;
            this.label1.Font = new System.Drawing.Font("Microsoft Sans Serif", 18F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label1.Location = new System.Drawing.Point(12, 41);
            this.label1.Name = "label1";
            this.label1.Size = new System.Drawing.Size(93, 29);
            this.label1.TabIndex = 15;
            this.label1.Text = "Imagen";
            // 
            // label2
            // 
            this.label2.AutoSize = true;
            this.label2.Font = new System.Drawing.Font("Microsoft Sans Serif", 18F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label2.Location = new System.Drawing.Point(215, 42);
            this.label2.Name = "label2";
            this.label2.Size = new System.Drawing.Size(191, 29);
            this.label2.TabIndex = 16;
            this.label2.Text = "Escala de grices";
            // 
            // label3
            // 
            this.label3.AutoSize = true;
            this.label3.Font = new System.Drawing.Font("Microsoft Sans Serif", 18F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label3.Location = new System.Drawing.Point(432, 42);
            this.label3.Name = "label3";
            this.label3.Size = new System.Drawing.Size(175, 29);
            this.label3.TabIndex = 17;
            this.label3.Text = "Agregar Ruido ";
            // 
            // label4
            // 
            this.label4.AutoSize = true;
            this.label4.Font = new System.Drawing.Font("Microsoft Sans Serif", 18F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label4.Location = new System.Drawing.Point(631, 42);
            this.label4.Name = "label4";
            this.label4.Size = new System.Drawing.Size(106, 29);
            this.label4.TabIndex = 18;
            this.label4.Text = "Mediana";
            // 
            // trackBarNoise
            // 
            this.trackBarNoise.Location = new System.Drawing.Point(453, 319);
            this.trackBarNoise.Name = "trackBarNoise";
            this.trackBarNoise.Size = new System.Drawing.Size(161, 45);
            this.trackBarNoise.TabIndex = 25;
            // 
            // label11
            // 
            this.label11.AutoSize = true;
            this.label11.Location = new System.Drawing.Point(434, 319);
            this.label11.Name = "label11";
            this.label11.Size = new System.Drawing.Size(21, 13);
            this.label11.TabIndex = 26;
            this.label11.Text = "0%";
            // 
            // label12
            // 
            this.label12.AutoSize = true;
            this.label12.Location = new System.Drawing.Point(606, 317);
            this.label12.Name = "label12";
            this.label12.Size = new System.Drawing.Size(33, 13);
            this.label12.TabIndex = 27;
            this.label12.Text = "100%";
            // 
            // btnMedian
            // 
            this.btnMedian.Image = global::Histograma_Local.Properties.Resources._1448139329_image;
            this.btnMedian.ImageAlign = System.Drawing.ContentAlignment.MiddleRight;
            this.btnMedian.Location = new System.Drawing.Point(636, 273);
            this.btnMedian.Name = "btnMedian";
            this.btnMedian.Size = new System.Drawing.Size(86, 40);
            this.btnMedian.TabIndex = 28;
            this.btnMedian.Text = "Alg Mediana";
            this.btnMedian.TextImageRelation = System.Windows.Forms.TextImageRelation.ImageBeforeText;
            this.btnMedian.UseVisualStyleBackColor = true;
            this.btnMedian.Click += new System.EventHandler(this.btnMedian_Click);
            // 
            // pbxBorde
            // 
            this.pbxBorde.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.pbxBorde.Location = new System.Drawing.Point(844, 74);
            this.pbxBorde.Name = "pbxBorde";
            this.pbxBorde.Size = new System.Drawing.Size(202, 193);
            this.pbxBorde.SizeMode = System.Windows.Forms.PictureBoxSizeMode.StretchImage;
            this.pbxBorde.TabIndex = 4;
            this.pbxBorde.TabStop = false;
            // 
            // label5
            // 
            this.label5.AutoSize = true;
            this.label5.Font = new System.Drawing.Font("Microsoft Sans Serif", 18F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.label5.Location = new System.Drawing.Point(839, 42);
            this.label5.Name = "label5";
            this.label5.Size = new System.Drawing.Size(91, 29);
            this.label5.TabIndex = 18;
            this.label5.Text = "Bordes";
            // 
            // chartBordes
            // 
            chartArea4.Name = "ChartArea1";
            this.chartBordes.ChartAreas.Add(chartArea4);
            legend4.Name = "Legend1";
            this.chartBordes.Legends.Add(legend4);
            this.chartBordes.Location = new System.Drawing.Point(835, 370);
            this.chartBordes.Name = "chartBordes";
            this.chartBordes.Palette = System.Windows.Forms.DataVisualization.Charting.ChartColorPalette.SeaGreen;
            series4.ChartArea = "ChartArea1";
            series4.Legend = "Legend1";
            series4.Name = "Pixels";
            this.chartBordes.Series.Add(series4);
            this.chartBordes.Size = new System.Drawing.Size(228, 136);
            this.chartBordes.TabIndex = 29;
            this.chartBordes.Text = "Original";
            // 
            // btnSaveBorde
            // 
            this.btnSaveBorde.Image = global::Histograma_Local.Properties.Resources._1448138637_filesave;
            this.btnSaveBorde.ImageAlign = System.Drawing.ContentAlignment.MiddleRight;
            this.btnSaveBorde.Location = new System.Drawing.Point(936, 273);
            this.btnSaveBorde.Name = "btnSaveBorde";
            this.btnSaveBorde.Size = new System.Drawing.Size(110, 40);
            this.btnSaveBorde.TabIndex = 30;
            this.btnSaveBorde.Text = "Save";
            this.btnSaveBorde.UseVisualStyleBackColor = true;
            this.btnSaveBorde.Click += new System.EventHandler(this.btnSaveBorde_Click);
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackColor = System.Drawing.Color.White;
            this.ClientSize = new System.Drawing.Size(1058, 667);
            this.Controls.Add(this.btnSaveBorde);
            this.Controls.Add(this.chartBordes);
            this.Controls.Add(this.btnMedian);
            this.Controls.Add(this.label12);
            this.Controls.Add(this.label11);
            this.Controls.Add(this.trackBarNoise);
            this.Controls.Add(this.label5);
            this.Controls.Add(this.label4);
            this.Controls.Add(this.label3);
            this.Controls.Add(this.label2);
            this.Controls.Add(this.label1);
            this.Controls.Add(this.chartLocal);
            this.Controls.Add(this.chartNoise);
            this.Controls.Add(this.chartGray);
            this.Controls.Add(this.btnExecuteLocal);
            this.Controls.Add(this.btnExecuteNoise);
            this.Controls.Add(this.btnExecuteGray);
            this.Controls.Add(this.btnSaveLocal);
            this.Controls.Add(this.btnSaveNoise);
            this.Controls.Add(this.btnSaveGray);
            this.Controls.Add(this.pbxBorde);
            this.Controls.Add(this.pbxLocal);
            this.Controls.Add(this.pbxNoise);
            this.Controls.Add(this.pbxGray);
            this.Controls.Add(this.pbxOriginal);
            this.Controls.Add(this.btnBrowse);
            this.Icon = ((System.Drawing.Icon)(resources.GetObject("$this.Icon")));
            this.Name = "Form1";
            this.Text = "TrabajoFinal";
            this.Load += new System.EventHandler(this.Form1_Load);
            ((System.ComponentModel.ISupportInitialize)(this.chartGray)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.chartNoise)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.chartLocal)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.pbxLocal)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.pbxNoise)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.pbxGray)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.pbxOriginal)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.trackBarNoise)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.pbxBorde)).EndInit();
            ((System.ComponentModel.ISupportInitialize)(this.chartBordes)).EndInit();
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.OpenFileDialog openFileDialog1;
        private System.Windows.Forms.Button btnBrowse;
        private System.Windows.Forms.PictureBox pbxOriginal;
        private System.Windows.Forms.PictureBox pbxGray;
        private System.Windows.Forms.PictureBox pbxNoise;
        private System.Windows.Forms.PictureBox pbxLocal;
        private System.Windows.Forms.Button btnSaveGray;
        private System.Windows.Forms.Button btnSaveNoise;
        private System.Windows.Forms.Button btnSaveLocal;
        private System.Windows.Forms.Button btnExecuteGray;
        private System.Windows.Forms.Button btnExecuteNoise;
        private System.Windows.Forms.Button btnExecuteLocal;
        private System.Windows.Forms.DataVisualization.Charting.Chart chartGray;
        private System.Windows.Forms.DataVisualization.Charting.Chart chartNoise;
        private System.Windows.Forms.DataVisualization.Charting.Chart chartLocal;
        private System.Windows.Forms.SaveFileDialog saveFileDialog1;
        private System.Windows.Forms.Label label1;
        private System.Windows.Forms.Label label2;
        private System.Windows.Forms.Label label3;
        private System.Windows.Forms.Label label4;
        private System.Windows.Forms.TrackBar trackBarNoise;
        private System.Windows.Forms.Label label11;
        private System.Windows.Forms.Label label12;
        private System.Windows.Forms.Button btnMedian;
        private System.Windows.Forms.PictureBox pbxBorde;
        private System.Windows.Forms.Label label5;
        private System.Windows.Forms.DataVisualization.Charting.Chart chartBordes;
        private System.Windows.Forms.Button btnSaveBorde;
    }
}

