<template>
  <div>
    <SessionsTable
      student ="Sessions"
      :student="Sessions"
    />
  </div>
</template>

<script>
import { mapState } from 'vuex'
import SessionsTable from '@/components/SessionsTable.vue'
export default {
  components: {
    SessionsTable
  },
  data() {
    return {}
  },
  computed: {
    ...mapState([
      'Sessions'
    ])
  }
}
</script>