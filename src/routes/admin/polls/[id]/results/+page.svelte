<script>
    export let data; // { polls, errorMsg? }
</script>

<section style="max-width: 720px; margin: 2rem auto;">
    <h1 style="margin-bottom: 0.5rem;">Results</h1>
    <p style="color: #666; margin-top: 0;">Poll ID: {data.pollId}</p>

    {#if data.errorMsg}
        <p style="color: crimson; margin-top: 1rem;">{data.errorMsg}</p>
    {:else if !data.insight}
        <p style="margin-top: 1rem;">Loading…</p>
    {:else}
        {#each data.insight.questions as q}
            <article style="border: 1px solid #e5e5e5; border-radius: 12px; padding: 1rem; margin-top: 1rem;">
                <h2 style="margin: 0 0 0.75rem 0; font-size: 1.1rem;">{q.question}</h2>
                <ul style="list-style: none; padding: 0; margin: 0;">
                    {#each q.voteOptions as o}
                        <li style="display: flex; justify-content: space-between; padding: 0.5rem 0; border-top: 1px dashed #eee;">
                            <span>{o.caption}</span>
                            <strong>{o.count}</strong>
                        </li>
                    {/each}
                </ul>
            </article>
        {/each}
    {/if}
</section>
