
var speedCanvas = document.getElementById("moneyChart");

Chart.defaults.global.defaultFontFamily = "Lato";
Chart.defaults.global.defaultFontSize = 18;

var dataFirst = {
	    label: "Total Money (€)",
	    data: [0, 590, 750, 200, 200, 550],
	    lineTension: 0.3,
	    fill: false,
	    borderColor: '#007bff',
	    backgroundColor: 'transparent',
	    pointBorderColor: '#007bff',
	    pointBackgroundColor: 'lightblue',
	    pointRadius: 5,
	    pointHoverRadius: 15,
	    pointHitRadius: 30,
	    pointBorderWidth: 2,
	    pointStyle: 'rect'
	  };


var speedData = {
		  labels: ["01/19", "02/19", "03/19", "04/19", "05/19", "06/19"],
		  datasets: [dataFirst]
		};


var chartOptions = {
		  legend: {
		    display: true,
		    position: 'top',
		    labels: {
		      boxWidth: 80,
		      fontColor: 'black'
		    }
		  }
		};

var lineChart = new Chart(speedCanvas, {
	  type: 'line',
	  data: speedData
	});