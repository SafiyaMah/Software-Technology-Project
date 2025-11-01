export async function load({ fetch }) {
    const res = await fetch('/api/admin/polls');
    if (!res.ok) {
        return { polls: [], errorMsg: `Failed to load polls (HTTP ${res.status})` };
    }
    return { polls: await res.json() };
}
