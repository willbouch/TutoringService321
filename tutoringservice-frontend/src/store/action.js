export default {
  addApproved: ({ commit }, payload) => {
    commit('approveSession', payload)
  }
}