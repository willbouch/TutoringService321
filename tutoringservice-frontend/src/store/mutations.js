export default {
  appendSession: (state, { status, student }) => {
    state[status].push(student)
  }
}