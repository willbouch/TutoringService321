  <template>
        <JqxScheduler ref="myScheduler"
          :width="getWidth" :height="600" :source="dataAdapter" :date="date" :showLegend="true" :view="'weekView'"
          :appointmentDataFields="appointmentDataFields" :resources="resources" :views="views"
      />
  </template>

  <script>
  export default {
        components: {
          JqxScheduler
      },
        data: function () {
          return {
              getWidth: getWidth('scheduler'),
            date: new jqx.date(2016, 11, 23),
            appointmentDataFields: 
            {
                from: 'start',
                to: 'end',
                id: 'id',
                description: 'description',
                location: 'location',
                subject: 'subject',
                resourceId: 'calendar'
            },
            resources:
            {
                colorScheme: 'scheme05',
                dataField: 'calendar',
                source: new jqx.dataAdapter(this.source)
            },
            views:
            [
                'dayView',
                'weekView',
                'monthView'
            ]
      }
  },
  beforeCreate: function () {
      const generateAppointments =  function () {
      const appointments = new Array();
      const appointment1 = {
          id: 'id1',
          description: 'George brings projector for presentations.',
          location: '',
          subject: 'Quarterly Project Review Meeting',
          calendar: 'Room 1',
          start: new Date(2016, 10, 23, 9, 0, 0),
          end: new Date(2016, 10, 23, 16, 0, 0)
          };
        appointments.push(appointment1);
        this.source =
          {
              dataType: 'array',
              dataFields: [
                  { name: 'id', type: 'string' },
                  { name: 'description', type: 'string' },
                  { name: 'location', type: 'string' },
                  { name: 'subject', type: 'string' },
                  { name: 'calendar', type: 'string' },
                  { name: 'start', type: 'date' },
                  { name: 'end', type: 'date' }
                ],
                  id: 'id',
                  localData: generateAppointments()
          };
          this.dataAdapter = new jqx.dataAdapter(this.source);
      }
  },

      mounted: function () {
              this.$refs.myScheduler.ensureAppointmentVisible('id1');
          },

          views:
  [
        { type: 'dayView', showWeekends: true, timeRuler: { hidden: false, timeZones: [{ id: 'UTC', text: 'UTC' }, { id: 'Pacific Standard Time', text: 'PST' }] } },
        { type: 'weekView', showWeekends: true, timeRuler: { hidden: false, timeZones: [{ id: 'UTC', text: 'UTC' }, { id: 'Pacific Standard Time', text: 'PST' }] } },
        'monthView',
        'agendaView'
  ]
  }
  this.$refs.myScheduler.setAppointmentProperty('id5', 'hidden', true);
  this.$refs.myScheduler.setAppointmentProperty('id6', 'hidden', true);
  this.$refs.myScheduler.endAppointmentsUpdate();
  </script>