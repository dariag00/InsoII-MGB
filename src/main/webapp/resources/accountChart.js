var pieChart = document.getElementById("accountsChart");

Chart.defaults.global.defaultFontFamily = "Lato";
Chart.defaults.global.defaultFontSize = 18;

var oilData = {
    labels: [
        "Account 1",
        "Account 2",
        "Account 3",
    ],
    datasets: [
        {
            data: [133.3, 86.2, 52.2],
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
