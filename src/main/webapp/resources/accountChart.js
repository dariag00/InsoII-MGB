var pieChart = document.getElementById("accountsChart");

Chart.defaults.global.defaultFontFamily = "Lato";
Chart.defaults.global.defaultFontSize = 18;

var oilData = {
    labels: "${values}",
    datasets: [
        {
            data: "${values}",
            backgroundColor: [
                "#4286f4",
                "#f4e841",
                "#84FF63"
            ]
        }]
};

var pieChart = new Chart(pieChart, {
  type: 'pie',
  data: oilData
});
