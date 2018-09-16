using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Drawing;
namespace Histograma_Local
{
    public class Algorithms
    {
        public static Random random = new Random();

        public static Image ConvertToGrayscale(Image originalImage)
        {
            Bitmap originalBitmap = new Bitmap(originalImage);

            for (int i = 0; i < originalImage.Height; i++)
            {
                for (int j = 0; j < originalImage.Width; j++)
                {
                    int r = originalBitmap.GetPixel(j, i).R;
                    int g = originalBitmap.GetPixel(j, i).G;
                    int b = originalBitmap.GetPixel(j, i).B;

                    int gray = (r + g + b) / 3;

                    Color color = Color.FromArgb(gray, gray, gray);

                    originalBitmap.SetPixel(j, i, color);

                }
            }

            return (Image)originalBitmap;
        }

        public static Image AddRandomNoiseToImage(Image originalImage, int noise)
        {
            Bitmap bitmap = new Bitmap(originalImage);



            for (int i = 0; i < noise; i++)
            {
                int x = random.Next(0, bitmap.Width - 1);
                int y = random.Next(0, bitmap.Height - 1);

                int randomColor = random.Next(0, 2);

                int colorValue = 0;
                if (randomColor == 0)
                {
                    colorValue = 0;
                }
                else
                {
                    colorValue = 255;
                }

                Color newColor = Color.FromArgb(colorValue, colorValue, colorValue);
                bitmap.SetPixel(x, y, newColor);

            }

            return (Image)bitmap;

        }
        public static Image AddNoiseToImage(Image originalImage, int noise)
        {


            float rnoise;
            float range = 2 * noise;

            Bitmap bitmap = new Bitmap(originalImage);

            if (noise >= 0 && noise <= 1)
            {
                for (int i = 0; i < bitmap.Height; i++)
                {
                    for (int j = 0; j < bitmap.Width; j++)
                    {

                        int r = bitmap.GetPixel(j, i).R;
                        int g = bitmap.GetPixel(j, i).G;
                        int b = bitmap.GetPixel(j, i).B;

                        rnoise = (float)random.NextDouble();

                        if (rnoise > 0.5)
                        {
                            rnoise = 1;
                        }
                        else
                        {
                            rnoise = 0;
                        }
                        r *= (int)rnoise;
                        g *= (int)rnoise;
                        b *= (int)rnoise;

                        Color newColor = Color.FromArgb(r, g, b);

                        bitmap.SetPixel(j, i, newColor);

                    }
                }

            }

            return (Image)bitmap;
        }


        public static Image LocalHistogramEqualization(Image originalImage, int maskDimensionX, int maskDimensionY)
        {
            Bitmap originalBitmap = new Bitmap(originalImage);
            int[,] matrix = new int[originalImage.Height + 2, originalImage.Width + 2];

            int matrixHeight = matrix.GetLength(0);
            int matrixwidth = matrix.GetLength(1);


            for (int i = 0; i < originalImage.Height + 2; i++)
            {
                for (int j = 0; j < originalImage.Width + 2; j++)
                {
                    if (i == 0 || j == 0 || j == originalBitmap.Width + 1 || i == originalBitmap.Height + 1)
                    {
                        matrix[i, j] = 0;
                    }
                    else
                    {
                        //Por mientras solo blanco y negro
                        matrix[i, j] = originalBitmap.GetPixel(j - 1, i - 1).R;

                    }
                }
            }

            List<int> uniqueList = GetUniqueElementsList(matrix);

            uniqueList.Sort();

            //Ya tengo la matriz rellena !!


            int middleXY = (maskDimensionX - 1) / 2;
            Dictionary<int, int> cumulativeDictionary;

            for (int i = 0; i < matrixHeight - (maskDimensionY - 1); i++)
            {
                for (int j = 0; j < matrixwidth - (maskDimensionX - 1); j++)
                {
                    int[,] subMatrix = GetSubMatrix(j, i, maskDimensionX, maskDimensionY, matrix);
                    //Chancar en la misma matriz o armar otra?
                    //Buscar pixel del medio de la subMatrix
                    cumulativeDictionary = GetCumulativeDistribution(subMatrix, uniqueList);

                    int middleElement = subMatrix[middleXY, middleXY];

                    int newValue = cumulativeDictionary[middleElement];
                    // matrix[i + middleXY, j + middleXY] = newValue;

                    Color newColor = Color.FromArgb(newValue, newValue, newValue);

                    originalBitmap.SetPixel(j + middleXY - 1, i + middleXY - 1, newColor);
                    // rpta[i + middleXY - 1, j + middleXY - 1] = newValue;

                    //int hueeex = 0;
                }
            }


            return originalBitmap;
        }

        public static Dictionary<int, int> GetCumulativeDistribution(int[,] matrix, List<int> uniqueList)
        {
            Dictionary<int, float> densityDictionary = GetListProbabilityDensity(matrix, uniqueList);
            Dictionary<int, int> cumulativeDictionary = new Dictionary<int, int>();



            float sum = 0;
            float myDensity = 0;
            int cumulativeValue = 0;


            foreach (int element in uniqueList)
            {
                myDensity = densityDictionary[element];

                cumulativeValue = (int)Math.Round(((double)(myDensity + sum)) * (double)(uniqueList.Count - 1));

                cumulativeDictionary.Add(element, cumulativeValue);

                sum += myDensity;
            }

            return cumulativeDictionary;
        }
        public static int GetUniqueElementsCount(int[,] matrix)
        {
            int height = matrix.GetLength(0);
            int width = matrix.GetLength(1);

            Dictionary<int, bool> auxDictionary = new Dictionary<int, bool>();
            for (int i = 0; i < height; i++)
            {
                for (int j = 0; j < width; j++)
                {
                    int element = matrix[i, j];

                    if (!auxDictionary.ContainsKey(element))
                        auxDictionary.Add(element, false);
                }
            }

            return auxDictionary.Count;

        }
        public static List<int> GetUniqueElementsList(int[,] matrix)
        {
            int height = matrix.GetLength(0);
            int width = matrix.GetLength(1);

            Dictionary<int, bool> auxDictionary = new Dictionary<int, bool>();


            for (int i = 0; i < height; i++)
            {
                for (int j = 0; j < width; j++)
                {
                    int element = matrix[i, j];

                    if (!auxDictionary.ContainsKey(element))
                        auxDictionary.Add(element, false);
                }
            }

            List<int> uniqueList = new List<int>();

            foreach (int key in auxDictionary.Keys)
            {
                uniqueList.Add(key);
            }

            return uniqueList;
        }

        public static Dictionary<int, float> GetListProbabilityDensity(int[,] matrix, List<int> list)
        {
            Dictionary<int, float> densityDictionary = new Dictionary<int, float>();

            foreach (int element in list)
            {
                float density = GetElementProbabilityDensity(matrix, element);
                densityDictionary.Add(element, density);
            }

            return densityDictionary;

        }
        public static Dictionary<int, float> GetMatrixProbabilityDensity(int[,] matrix)
        {
            Dictionary<int, float> densityDictionary = new Dictionary<int, float>();

            int height = matrix.GetLength(0);
            int width = matrix.GetLength(1);

            for (int i = 0; i < height; i++)
            {
                for (int j = 0; j < width; j++)
                {
                    int element = matrix[i, j];

                    if (!densityDictionary.ContainsKey(element))
                    {
                        float density = GetElementProbabilityDensity(matrix, element);
                        densityDictionary.Add(element, density);
                    }
                }
            }


            return densityDictionary;

        }

        public static int[,] GetSubMatrix(int x, int y, int width, int height, int[,] originalMatrix)
        {
            int[,] newMatrix = new int[width, height];

            for (int i = 0; i < height; i++)
            {
                for (int j = 0; j < width; j++)
                {
                    int currentX = x + j;
                    int currentY = y + i;

                    newMatrix[i, j] = originalMatrix[currentY, currentX];
                }
            }

            return newMatrix;

        }
        public static float GetElementProbabilityDensity(int[,] matrix, int target)
        {
            float count = 0;
            float totalElements = matrix.GetLength(0) * matrix.GetLength(1);


            for (int i = 0; i < matrix.GetLength(0); i++)
            {
                for (int j = 0; j < matrix.GetLength(1); j++)
                {
                    if (matrix[i, j] == target)
                        ++count;
                }
            }

            return count / totalElements;

        }

        public static Dictionary<int, int> GetHistogramData(Image image)
        {
            Dictionary<int, int> resultDictionary = new Dictionary<int, int>();

            Bitmap bitmap = new Bitmap(image);


            for (int i = 0; i < bitmap.Height; i++)
            {
                for (int j = 0; j < bitmap.Width; j++)
                {
                    int pixelValue = bitmap.GetPixel(j, i).R;
                    if (!resultDictionary.ContainsKey(pixelValue))
                    {
                        resultDictionary.Add(pixelValue, 1);
                    }
                    else
                    {
                        ++resultDictionary[pixelValue];
                    }


                }
            }

            return resultDictionary;
        }
        //public static Image GlobalEqualization(Image image)
        //{
        //    Bitmap bitmap = new Bitmap(image);
        //    int[,] matriz = new int[bitmap.Height, bitmap.Width];

        //    for (int i = 0; i < bitmap.Height; i++)
        //    {
        //        for (int j = 0; j < bitmap.Width; j++)
        //        {
        //            int pixelValue = bitmap.GetPixel(j, i).R;
        //            matriz[i, j] = pixelValue;
        //        }
        //    }

        //    List<int> uniqueList = GetUniqueElementsList(matriz);
        //    uniqueList.Sort();


        //    Dictionary<int, int> cumulativeDictionary = GetCumulativeDistribution(matriz, uniqueList);
        //    for (int i = 0; i < bitmap.Height; i++)
        //    {
        //        for (int j = 0; j < bitmap.Width; j++)
        //        {
        //            int newPixelValue = cumulativeDictionary[matriz[i, j]];

        //            Color newColor = Color.FromArgb(newPixelValue, newPixelValue, newPixelValue);

        //            bitmap.SetPixel(j, i, newColor);


        //        }
        //    }
        //    return (Image)bitmap;
        //}
        public static Image MedianEqualization(Image image)
        {
            Bitmap bitmap = new Bitmap(image);
            int[,] matriz = new int[bitmap.Height, bitmap.Width];

            for (int i = 0; i < bitmap.Height; i++)
            {
                for (int j = 0; j < bitmap.Width; j++)
                {
                    int pixelValue = bitmap.GetPixel(j, i).R;
                    matriz[i, j] = pixelValue;
                }
            }

            int[] dx = { 0, 0, 0, -1, -1, -1, 1, 1, 1 };
            int[] dy = { -1, 1, 0, -1, 1, 0, -1, 1, 0 };
            for (int i = 0; i < bitmap.Height; i++)
            {
                for (int j = 0; j < bitmap.Width; j++)
                {
                    int[] Valores = new int[10];
                    int cont = 0;
                    for (int dir = 0; dir < 9; dir++)
                    {

                        int di = i + dx[dir];
                        int dj = j + dy[dir];
                        if (di >= 0 && di < bitmap.Height && dj >= 0 && dj < bitmap.Width)
                            Valores[cont++] = matriz[di, dj];
                    }

                    for (int l = 0; l < cont - 1; l++)
                        for (int ll = l + 1; ll < cont; ll++)
                        {
                            if (Valores[l] > Valores[ll])
                            {
                                int t = Valores[l];
                                Valores[l] = Valores[ll];
                                Valores[ll] = t;
                            }
                        }


                            if (cont % 2 == 0)
                            {
                                int val = (Valores[cont / 2] + Valores[cont / 2 - 1]) / 2;
                                Color newColor = Color.FromArgb(val, val, val);
                                bitmap.SetPixel(j, i, newColor);
                            }
                            else
                            {
                                int val = Valores[cont / 2];
                                Color newColor = Color.FromArgb(val, val, val);
                                bitmap.SetPixel(j, i, newColor);
                            }

                }
            }


            return (Image)bitmap;
        }



    }
}