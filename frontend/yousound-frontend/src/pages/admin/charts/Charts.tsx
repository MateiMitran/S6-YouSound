import * as React from "react";
import { Bar, Line, Doughnut } from "react-chartjs-2";
import { Chart } from "chart.js";
import { registerables } from "chart.js";
import { Box } from "@mui/material";

// Register the required elements and scales
Chart.register(...registerables);
interface ChartData {
  labels: string[];
  datasets: {
    label: string;
    data: number[];
    backgroundColor?: string[];
  }[];
}

export const Charts: React.FC = () => {
  const topGenresChartData: ChartData = {
    labels: ["Rock", "Pop", "Hip-Hop", "Electronic", "Jazz", "Country"],
    datasets: [
      {
        label: "Top Genres",
        data: [45, 35, 25, 20, 15, 10],
        backgroundColor: [
          "rgba(255, 99, 132, 0.2)",
          "rgba(54, 162, 235, 0.2)",
          "rgba(255, 206, 86, 0.2)",
          "rgba(75, 192, 192, 0.2)",
          "rgba(153, 102, 255, 0.2)",
          "rgba(255, 159, 64, 0.2)",
        ],
      },
    ],
  };

  const topSongsChartData: ChartData = {
    labels: ["Song A", "Song B", "Song C", "Song D", "Song E"],
    datasets: [
      {
        label: "Top Songs this Month",
        data: [1500, 1200, 1800, 1900, 2400],
        backgroundColor: ["rgba(75, 192, 192, 0.2)"],
      },
    ],
  };

  const topArtistsChartData: ChartData = {
    labels: ["Artist A", "Artist B", "Artist C", "Artist D", "Artist E"],
    datasets: [
      {
        label: "Top Artists this Year",
        data: [5000, 4000, 3500, 3000, 2500],
        backgroundColor: [
          "rgba(255, 99, 132, 0.2)",
          "rgba(54, 162, 235, 0.2)",
          "rgba(255, 206, 86, 0.2)",
          "rgba(75, 192, 192, 0.2)",
          "rgba(153, 102, 255, 0.2)",
        ],
      },
    ],
  };

  return (
    <Box sx={{ display: "flex", margin: "0 auto" }}>
      <Box>
        <h2>Top Genres</h2>
        <Bar data={topGenresChartData} />
      </Box>
      <Box>
        <h2>Top Songs this Month</h2>
        <Line data={topSongsChartData} />
      </Box>
      <Box>
        <h2>Top Artists this Year</h2>
        <Doughnut data={topArtistsChartData} />
      </Box>
    </Box>
  );
};
