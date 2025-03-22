import java.io.*;
import java.util.*;
public class StockTrendAnalyzer
{
    public static void main(String args[]) throws IOException
    {
        String filePath = "/Users/prabalsood/IdeaProjects/Stock Price Trend Analyzer/src/stock_data.csv";//CSV file path
        List<Double> prices = loadPrices(filePath);

        int shortWindow = 3;   //Short term SMA
        int longWindow = 5;    //Long term SMA

        double shortSMA = calculateSMA(prices, shortWindow);
        double longSMA = calculateSMA(prices, longWindow);

        System.out.println("Short-term (" + shortWindow + "-day) SMA: " + shortSMA);
        System.out.println("Long-term (" + longWindow + "-day) SMA: " + longSMA);

        if (shortSMA > longSMA)
        {
            System.out.println("The stock is in an Uptrend!");
        }
        else
        {
            System.out.println("The stock is in a Downtrend!");
        }
    }

    //Load stock prices from CSV file
    static List<Double> loadPrices(String filename) throws IOException
    {
        List<Double> prices = new ArrayList<>();
        BufferedReader br = new BufferedReader(new FileReader(filename));
        String line;
        br.readLine();   //Skips the first line (CSV header)
        while((line = br.readLine())!=null)
        {
            prices.add(Double.parseDouble(line.split(",")[1]));
        }
        br.close();
        return prices;
    }

    //Calcilating SMA(Simple Moving Average)
    static double calculateSMA(List<Double> prices, int period)
    {
        if(prices.size()<period)
            return 0;
        double sum = 0;
        for(int i = prices.size() - period; i < prices.size();i++)
        {
            sum = sum + prices.get(i);
        }
        return sum/period;
    }
}
