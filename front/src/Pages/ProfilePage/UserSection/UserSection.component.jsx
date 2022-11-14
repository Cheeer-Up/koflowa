import React from "react"

import AvatarCard from "./AvatarCard/AvatarCard.component"
import ContentCard from "./ContentCard/ContentCard.component"

import "./UserSection.styles.scss"

const UserSection = ({ user }) => (
  <div className='grid'>
    <AvatarCard id={user.seq} profile={user.profile} />
    <ContentCard
      username={user.nickname}
      user_about={user.about}
      answers_count={user.answers_count}
      posts_count={user.posts_count}
      comments_count={user.comments_count}
      tags_count={user.tags_count}
      created_at={user.createdTime}
    />
  </div>
)

export default UserSection
