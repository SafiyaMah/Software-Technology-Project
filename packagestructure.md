# Project Structure

Current description of the structure of the project
[subject to change]

```text
com.example.medipoll
├─ DocpollApplication
├─ admin
│  ├─ api
│  │  └─ AdminPollController
│  ├─ dto
│  │  ├─ InsightView
│  │  └─ PollAdminView
│  └─ service
│     ├─ AdminPollService
│     └─ AdminPollServiceImp
├─ domain
│  ├─ Poll
│  ├─ User
│  ├─ Vote
│  └─ VoteOption
├─ patient
│  ├─ api
│  │  └─ PatientPollController
│  ├─dto
│  └─ service
│     ├─ PatientPollService
│     └─ PatientPollServiceImp
├─repository
│  ├─ PollRepository
│  ├─ UserRepository
│  ├─ VoteOptionRepository
│  └─ VoteReporistory
├─security
└─common