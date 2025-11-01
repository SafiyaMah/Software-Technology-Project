export async function load({ params, fetch }) {
    const pollId = params.id;

    const res = await fetch(`/api/admin/polls/${pollId}/insights`);
    if (!res.ok) {
        return { pollId, errorMsg: `Failed to load results (HTTP ${res.status})` };
    }

    const insight = await res.json();
    return { pollId, insight };
}

