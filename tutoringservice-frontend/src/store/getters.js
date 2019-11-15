export default {
  sessionCount: (state) => {
    return state.Sessions.length
  },
  getAllApprovedSessions: (state) => {
    return state.sessionsToApprove.filter((sessionsToApprove) => {
      return sessionsToApprove.status === 'approved'
    })
  }
}