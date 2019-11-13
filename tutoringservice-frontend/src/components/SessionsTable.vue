<template>
  <div>
    <h1>Sessions</h1>
    <b-table striped hover :items="sessionsToApprove">
      <template slot="name" slot-scope="data">
        <router-link :to="`sessionsToApprove/${status}/${data.index}`">
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
    sessionsToApprove: Array
  }
}
</script>