<template>
  <div>
    <h1>Sessions</h1>
    <b-table striped hover :items="sessions">
      <template slot="name" slot-scope="data">
        <router-link :to="`sessions/${status}/${data.index}`">
          {{ data.value }}
        </router-link>
      </template>
    </b-table>
  </div>
</template>

<script>
export default {
  props: {
    status: String,
    sessions: Array
  }
}
</script>