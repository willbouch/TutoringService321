<template>
  <div id="app">
    <vue-good-table :columns="columns" :rows="rows">
      <template slot="table-row" slot-scope="props">
        <span v-if="props.column.field === 'details'">
          <button type="button" class="btn btn-primary">View Details</button>
        </span>
        <span v-else> {{ props.formattedRow[props.column.field] }} </span>
      </template>
    </vue-good-table>
  </div>
</template>

<script>
export default {
  name: "App",
  data() {
    return {
      columns: [
        {
          label: "Date",
          field: "date",
          filterable: true
        },
        {
          label: "Event",
          field: "event",
          filterable: true
        },
        {
          label: "Details",
          field: "details",
          filterable: true
        }
      ],
      rows: [
        {
          event: "Thanksgiving Barrel Events",
          details: "View Event",
          date: "11/28/2018 at 6:34 PM"
        },
        {
          event: "Christmas Barrel Events",
          details: "View Event",
          date: "12/25/2018 at 6:34 PM"
        },
        {
          event: "Thanksgiving Barrel Events",
          details: "View Event",
          date: "11/28/2018 at 6:34 PM"
        }
      ]
    };
  }
};
</script>
