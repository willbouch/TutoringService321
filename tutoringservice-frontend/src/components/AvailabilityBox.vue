<template>
<div id="jquery-script-menu">
<div class="jquery-script-center">
<ul>
<li><a href="http://www.jqueryscript.net/time-clock/Create-A-Basic-Weekly-Schedule-with-Hour-Selector-Using-jQuery.html">Download This Plugin</a></li>
<li><a href="http://www.jqueryscript.net/">Back To jQueryScript.Net</a></li>
</ul>
<div class="jquery-script-clear"></div>
<div id="day-schedule"></div>
<h1 style="margin:150px auto 30px auto;">Day Schedule Selector jQuery Plugin Demo</h1>
</div>
</div>
</template>

<script src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
  <script src="../src/index.js"></script>
  <script>
    (function ($) {
      $("#day-schedule").dayScheduleSelector({
        /*
        days: [1, 2, 3, 5, 6],
        interval: 15,
        startTime: '09:50',
        endTime: '21:06'
        */
      });
      $("#day-schedule").on('selected.artsy.dayScheduleSelector', function (e, selected) {
        console.log(selected);
      })
      $("#day-schedule").data('artsy.dayScheduleSelector').deserialize({
        '0': [['09:30', '11:00'], ['13:00', '16:30']]
      });
    })($);



  var _gaq = _gaq || [];
  _gaq.push(['_setAccount', 'UA-36251023-1']);
  _gaq.push(['_setDomainName', 'jqueryscript.net']);
  _gaq.push(['_trackPageview']);

  (function() {
    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
  })();
  </script>

<style>

.schedule-rows td {
  width: 80px;
  height: 30px;
  margin: 3px;
  padding: 5px;
  background-color: #3498DB;
  cursor: pointer;
}

.schedule-rows td:first-child {
  background-color: transparent;
  text-align: right;
  position: relative;
  top: -12px;
}

.schedule-rows td[data-selected],
.schedule-rows td[data-selecting] { background-color: #E74C3C; }

.schedule-rows td[data-disabled] { opacity: 0.55; }

</style>
