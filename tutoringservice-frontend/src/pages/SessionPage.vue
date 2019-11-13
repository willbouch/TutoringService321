<template>
  <div>
    <SessionsTable
      status ="pending"
      :Sessions="pending"
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