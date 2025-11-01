<script>
    export let data; // { polls, errorMsg? }
</script>

<section style="max-width: 820px; margin: 2rem auto;">
    <h1 style="margin: 0 0 0.5rem 0;">Polls</h1>
    <p style="color:#666; margin:0 0 1rem 0;">Select a poll to view results.</p>

    {#if data.errorMsg}
        <p style="color:crimson">{data.errorMsg}</p>
    {:else if !data.polls?.length}
        <p>No polls yet.</p>
    {:else}
        <table style="width:100%; border-collapse: collapse;">
            <thead>
            <tr style="text-align:left; border-bottom:1px solid #eee;">
                <th style="padding:0.5rem 0;">Title</th>
                <th style="padding:0.5rem 0;">Created</th>
                <th style="padding:0.5rem 0;">Status</th>
                <th style="padding:0.5rem 0;"></th>
            </tr>
            </thead>
            <tbody>
            {#each data.polls as p}
                <tr style="border-bottom:1px solid #f3f3f3;">
                    <td style="padding:0.5rem 0.25rem;">{p.question}</td>
                    <td style="padding:0.5rem 0.25rem; color:#666;">
                        {new Date(p.createdTime).toLocaleString()}
                    </td>
                    <td style="padding:0.5rem 0.25rem;">{p.completed ? 'Closed' : 'Open'}</td>
                    <td style="padding:0.5rem 0.25rem; text-align:right;">
                        <a href={`/admin/polls/${p.pollId}/results`} style="text-decoration:none;">
                            View results →
                        </a>
                    </td>
                </tr>
            {/each}
            </tbody>
        </table>
    {/if}
</section>
